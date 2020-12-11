package com.example.facerecognition.bean;

public class FacesBean {
    /**
     * face_token : 7357f18fb42325c5d5d82b56fbbd9329
     * face_rectangle : {"top":198,"left":138,"width":255,"height":255}
     * attributes : {"gender":{"value":"Female"},"age":{"value":33}}
     */

    private String face_token;
    private FaceRectangleBean face_rectangle;
    private AttributesBean attributes;

    public String getFace_token() {
        return face_token;
    }

    public void setFace_token(String face_token) {
        this.face_token = face_token;
    }

    public FaceRectangleBean getFace_rectangle() {
        return face_rectangle;
    }

    public void setFace_rectangle(FaceRectangleBean face_rectangle) {
        this.face_rectangle = face_rectangle;
    }

    public AttributesBean getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesBean attributes) {
        this.attributes = attributes;
    }

    public static class FaceRectangleBean {
        /**
         * top : 198
         * left : 138
         * width : 255
         * height : 255
         */

        private int top;
        private int left;
        private int width;
        private int height;

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

}
