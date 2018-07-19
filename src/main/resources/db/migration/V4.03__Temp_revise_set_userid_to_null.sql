ALTER table image
drop column s3key;
ALTER table image
add column s3key varchar(255) DEFAULT 'default_s3key';