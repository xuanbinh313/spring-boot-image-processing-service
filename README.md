# Image Processing Service
The following was discovered as part of building this project:

* The original package name 'com.binhcodev.spring-boot-image-processing-service' is invalid and this project uses 'com.binhcodev.spring_boot_image_processing_service' instead.

# Getting Started

### Reference Documentation
https://roadmap.sh/projects/image-processing-service


DATABASE
```sql
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(100),
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
)

DROP TABLE IF EXISTS images;

CREATE TABLE images (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
)
```