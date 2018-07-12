ALTER table image
Add column users_id bigint not null;
ALTER table image ADD CONSTRAINT fk_image_users
    FOREIGN KEY(users_id) REFERENCES image(id);