package gabriel.atrack.activity.repository.si;

import gabriel.atrack.activity.model.Activity_;
import gabriel.atrack.activity.repository.ActivityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.Message;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateActivityMessageHandler implements Runnable {

	final static int RECEIVE_TIMEOUT = 1000;
	private QueueChannel channel;

	@Autowired
	@Qualifier("activityRepositoryJpa")
	private ActivityRepository activityRepository;

	@Value("#{activityChannel}")
	public void setChannel(QueueChannel channel) {
		System.out.println("REceiver is waiting");
		this.channel = channel;
	}

	@PostConstruct
	public void startTheListner() {
		System.out.println("Receiver started");
		new Thread(this).start();
	}

	private ExecutorService executor = Executors.newFixedThreadPool(1);

	private class BatchCreater implements Callable<Boolean> {

		private final List<Activity_> batch;
		private int counter;

		public BatchCreater(List<Activity_> batch) {

			this.batch = batch;
		}

		@Override
		public Boolean call() throws Exception {
			synchronized (batch) {
				System.out.println("IN CALLLLL");
				Message<?> activity;

				counter = 0;

				while (!Thread.currentThread().isInterrupted()) {
					try {
						activity = channel.receive(RECEIVE_TIMEOUT);
						if (activity != null) {
							System.out.println("got a activity");
							batch.add((Activity_) activity.getPayload());
							counter++;
						}
						if (counter == 11) {
							return true;
						}

					} catch (Exception ie) {
						System.out.println("LOG THIS CASE");
						break;
					}
				}
				System.out.println("returning from thread");
				return (counter > 0);
			}
		}

		public int getCounter() {
			return counter;
		}

	};

	public void handleTicketMessage() {
		while (true) {
			ArrayList<Activity_> activityList = new ArrayList<>(20);
			System.out.println("Spawning a hread");
			Future<Boolean> result = executor.submit(new BatchCreater(
					activityList));

			try {
				
				result.get(RECEIVE_TIMEOUT, TimeUnit.MILLISECONDS);
			} catch (InterruptedException | ExecutionException
					| TimeoutException e) {
				result.cancel(true);
			}

			synchronized (activityList) {
				if (activityList.size() > 0) {
					handleTicket(activityList);
				}

			}

		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	private void handleTicket(ArrayList<Activity_> activityList) {

		getActivityRepository().saveActivity(
				activityList.toArray(new Activity_[0]));
		
		//dispatch the message to event server for post create activity
		System.out.println("Received Activity ");
	}

	protected ActivityRepository getActivityRepository() {
		return activityRepository;
	}

	@Override
	public void run() {
		handleTicketMessage();
	}

}
