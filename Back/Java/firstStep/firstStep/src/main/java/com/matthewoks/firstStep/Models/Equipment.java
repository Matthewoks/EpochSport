package com.matthewoks.firstStep.Models;

    public class Equipment {

        private Long id;
        private String name;
        private String category;
        private String imageUrl;

        public Equipment() {
        }

        public Equipment(Long id, String name, String category, String imageUrl) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.imageUrl = imageUrl;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
