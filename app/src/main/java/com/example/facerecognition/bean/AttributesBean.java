package com.example.facerecognition.bean;

public  class AttributesBean {
    /**
     * gender : {"value":"Female"}
     * age : {"value":33}
     */

    private GenderBean gender;
    private AgeBean age;

    public GenderBean getGender() {
        return gender;
    }

    public void setGender(GenderBean gender) {
        this.gender = gender;
    }

    public AgeBean getAge() {
        return age;
    }

    public void setAge(AgeBean age) {
        this.age = age;
    }

    public static class GenderBean {
        /**
         * value : Female
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class AgeBean {
        /**
         * value : 33
         */

        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
