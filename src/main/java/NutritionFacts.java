public interface NutritionFacts {
    double PROTEIN_ENERGY = 4.1;
    double FAT_ENERGY = 9.29;
    double CARBOHYDRATE_ENERGY = 4.1;

    double getEnergy();

    void setEnergy(double energy);

    double getProtein();

    void setProtein(double protein);

    double getFat();

    void setFat(double fat);

    double getCarbohydrate();

    void setCarbohydrate(double carbohydrate);

}