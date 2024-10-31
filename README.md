# Image Processing Service
The following was discovered as part of building this project:

* The original package name 'com.binhcodev.spring-boot-image-processing-service' is invalid and this project uses 'com.binhcodev.spring_boot_image_processing_service' instead.

## Getting Started

### Reference Documentation
https://roadmap.sh/projects/image-processing-service
#### Add docker
```sh
docker run -d --hostname rabbitmq-host --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

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

## Checklist

### User Authentication
1. Sign-Up: Allow users to create an account.
2. Log-In: Allow users to log into their account.
3. JWT Authentication: Secure endpoints using JWTs for authenticated access.

### Image Management
1. Upload Image: Allow users to upload images.
2. Transform Image: Allow users to perform various transformations (resize, crop, rotate, watermark etc.).
    - Resize -> Done
    - Crop -> Done
    - Rotate -> Done
    - Watermark
    - Flip
    - Mirror
    - Compress
    - Change format (JPEG, PNG, etc.)
    - GrayScale -> Done
    - Sepia
3. Retrieve Image: Allow users to retrieve a saved image in different formats.
4. List Images: List all uploaded images by the user with metadata (pagination,limit)

### Tips
- Use a cloud storage service like AWS S3, Cloudflare R2, or Google Cloud Storage to store images.
- Use some image processing libraries to apply transformations. -> Done
- Put a rate limit on image transformations to prevent abuse.
- Consider caching transformed images to improve performance. -> Done
- Implement error handling and validation for all endpoints. -> Done
- Optionally use a message queue like RabbitMQ or Kafka to process image transformations asynchronously. -> Done
