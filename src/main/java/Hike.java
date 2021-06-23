import java.util.List;

public interface Hike {
    String getHikeName();

    void setHikeName(String hikeName);

    int getDuration();

    void setDuration(int duration);

    int getNumberOfParticipants();

    void setNumberOfParticipants(int numberOfParticipants);

    List<HikeDay> getHikeDays();

    HikeDay getHikeDay(int n);

    void addHikeDay();

    void moveHikeDay(int hikeDayNumber, int position);

    void removeHikeDay(int numberOfDay);
}
