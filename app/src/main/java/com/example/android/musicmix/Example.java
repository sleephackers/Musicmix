package com.example.android.musicmix;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Example {

        @SerializedName("message")
        @Expose
        private Message message;

        /**
         * No args constructor for use in serialization
         *
         */
        public Example() {
        }

        /**
         *
         * @param message
         */
        public Example(Message message) {
            super();
            this.message = message;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

    }

