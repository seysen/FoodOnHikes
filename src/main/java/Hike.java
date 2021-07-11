import java.util.List;

public interface Hike {
    List<HikeDay> getHikeDays();

    String getHikeName();

    void setHikeName(String hikeName);

    int getDuration();

    void setDuration(int duration);

    int getNumberOfParticipants();

    void setNumberOfParticipants(int numberOfParticipants);

    HikeDay getHikeDay(int n);

    void moveHikeDay(int hikeDayNumber, int position);

    void removeHikeDay(int numberOfDay);

    ShoppingCart getShoppingCart();
}
