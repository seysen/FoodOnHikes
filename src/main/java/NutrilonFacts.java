public interface NutrilonFacts {
    double PROTEINENERGY = 4.1;
    double FATENERGY = 9.29;
    double CARBOHYDRATEENERGY = 4.1;

    double getEnergy();

    void setEnergy(double energy);

    double getProtein();

    void setProtein(double protein);

    double getFat();

    void setFat(double fat);

    double getCarbohydrate();

    void setCarbohydrate(double carbohydrate);

}