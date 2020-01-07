package ru.mail.druk_aleksandr.repository.model;

public class Car {
    private String name;
    private String carModel;
    private Integer engineCapacity;

    private Car(Builder builder) {
        name = builder.name;
        carModel = builder.carModel;
        engineCapacity = builder.engineCapacity;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getCarModel() {
        return carModel;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", carModel='" + carModel + '\'' +
                ", engineCapacity=" + engineCapacity +
                '}';
    }

    public static final class Builder {
        private String name;
        private String carModel;
        private Integer engineCapacity;

        private Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder carModel(String val) {
            carModel = val;
            return this;
        }

        public Builder engineCapacity(Integer val) {
            engineCapacity = val;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
