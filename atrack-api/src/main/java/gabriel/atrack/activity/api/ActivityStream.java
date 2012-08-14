package gabriel.atrack.activity.api;

import gabriel.atrack.dto.ActivitySummary;

import java.util.List;

public interface ActivityStream {

	List<ActivitySummary> getStream(Long ts);
}
