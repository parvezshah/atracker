package gabriel.atrack.activity.repository.si;

import gabriel.atrack.activity.model.Activity_;
import gabriel.atrack.activity.repository.ActivityRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component("activityRepositoryImpl")
public class ActivityRepositoryImpl implements ActivityRepository {

	private MessageChannel channel;

	@Value("#{activityChannel}")
	public void setChannel(MessageChannel channel) {
		this.channel = channel;
	}

	/*
	 * private MessagingTemplate messagingTemplate;
	 * 
	 * @Autowired public void setMessagingTemplate(MessagingTemplate
	 * messagingTemplate) { this.messagingTemplate = messagingTemplate; }
	 */

	@Override
	public void saveActivity(Activity_ activity) {
		channel.send(MessageBuilder.withPayload(activity).build());

		// messagingTemplate.convertAndSend(activity);
		System.out.println("Send Activity for save");
	}

	@Override
	public void saveActivity(Activity_[] activity) {
		throw new UnsupportedOperationException("saveActivity");
	}

	@Override
	public List<Activity_> getActivity(Long ts, Integer maxRowCount) {
		throw new UnsupportedOperationException("getActivity");
	}

}
