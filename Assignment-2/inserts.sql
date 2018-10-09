use `assignment_2`;
INSERT INTO role(id) values('OWNER');
INSERT INTO role(id) values('ADMIN');
INSERT INTO role(id) values('WRITER');
INSERT INTO role(id) values('EDITOR');
INSERT INTO role(id) values('REVIEWER');

INSERT INTO privilege(id) values('CREATE');
INSERT INTO privilege(id) values('READ');
INSERT INTO privilege(id) values('UPDATE');
INSERT INTO privilege(id) values('DELETE');

-- Insert users/developers
INSERT INTO `person` VALUES (12, 'Alice', 'Wonder', 'alice', 'alice', 'alice@wonder.com', NULL );
INSERT INTO `developer` VALUES ( 12, '4321rewq' );

INSERT INTO `person` VALUES (23, 'Bob', 'Marley', 'bob', 'bob', 'bob@marley.com', NULL );
INSERT INTO `developer` VALUES ( 23, '5432trew' );

INSERT INTO `person` VALUES (34, 'Charlie', 'Garcia', 'charlie', 'charlie', 'chuch@garcia.com', NULL );
INSERT INTO `developer` VALUES ( 34, '6543ytre' );

INSERT INTO `person` VALUES (45, 'Dan', 'Martin', 'dan', 'dan', 'dan@martin.com', NULL );
INSERT INTO `user` VALUES ( 45, NULL );

INSERT INTO `person` VALUES (56, 'Ed', 'Karaz', 'ed', 'ed', 'ed@kar.com', NULL );
INSERT INTO `user` VALUES ( 56, NULL );

-- Insert websites
INSERT INTO `website` VALUES (123, 'Facebook', 'an online social media and social networking service', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1234234, 12 );
INSERT INTO `website_role` VALUES ( NULL, 'OWNER', 12, 123 );
INSERT INTO `website_role` VALUES ( NULL, 'EDITOR', 23, 123 );
INSERT INTO `website_role` VALUES ( NULL, 'ADMIN', 34, 123 );

INSERT INTO `website` VALUES (234, 'Twitter', 'an online news and social networking service', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 4321543, 23 );
INSERT INTO `website_role` VALUES ( NULL, 'OWNER', 23, 234 );
INSERT INTO `website_role` VALUES ( NULL, 'EDITOR', 34, 234 );
INSERT INTO `website_role` VALUES ( NULL, 'ADMIN', 12, 234 );

INSERT INTO `website` VALUES (345, 'Wikipedia', 'a free online encyclopedia', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 3456654, 34 );
INSERT INTO `website_role` VALUES ( NULL, 'OWNER', 34, 345 );
INSERT INTO `website_role` VALUES ( NULL, 'EDITOR', 12, 345 );
INSERT INTO `website_role` VALUES ( NULL, 'ADMIN', 23, 345 );

INSERT INTO `website` VALUES (456, 'CNN', 'an American basic cable and satellite television news channel', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 6543345, 12 );
INSERT INTO `website_role` VALUES ( NULL, 'OWNER', 12, 456 );
INSERT INTO `website_role` VALUES ( NULL, 'EDITOR', 23, 456 );
INSERT INTO `website_role` VALUES ( NULL, 'ADMIN', 34, 456 );

INSERT INTO `website` VALUES (567, 'CNET', 'an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 5433455, 23 );
INSERT INTO `website_role` VALUES ( NULL, 'OWNER', 23, 567 );
INSERT INTO `website_role` VALUES ( NULL, 'EDITOR', 34, 567 );
INSERT INTO `website_role` VALUES ( NULL, 'ADMIN', 12, 567 );

INSERT INTO `website` VALUES (678, 'Gizmodo', 'a design, technology, science and science fiction website that also writes articles on politics', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 4322345, 34 );
INSERT INTO `website_role` VALUES ( NULL, 'OWNER', 34, 678 );
INSERT INTO `website_role` VALUES ( NULL, 'EDITOR', 12, 678 );
INSERT INTO `website_role` VALUES ( NULL, 'ADMIN', 23, 678 );

-- Insert pages
INSERT INTO `page` VALUES (123, 'Home', 'Landing page', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 123434, 567 );
INSERT INTO `page_role` VALUES ( NULL, 'EDITOR', 12, 123 );
INSERT INTO `page_role` VALUES ( NULL, 'REVIEWER', 23, 123 );
INSERT INTO `page_role` VALUES ( NULL, 'WRITER', 34, 123 );

INSERT INTO `page` VALUES (234, 'About', 'Website description', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 234545, 678 );
INSERT INTO `page_role` VALUES ( NULL, 'EDITOR', 23, 234 );
INSERT INTO `page_role` VALUES ( NULL, 'REVIEWER', 34, 234 );
INSERT INTO `page_role` VALUES ( NULL, 'WRITER', 12, 234 );

INSERT INTO `page` VALUES (345, 'Contact', 'Addresses, phones, and contact info', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 345656, 345 );
INSERT INTO `page_role` VALUES ( NULL, 'EDITOR', 34, 345 );
INSERT INTO `page_role` VALUES ( NULL, 'REVIEWER', 12, 345 );
INSERT INTO `page_role` VALUES ( NULL, 'WRITER', 23, 345 );

INSERT INTO `page` VALUES (456, 'Preferences', 'Where users can configure their preferences', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 456776, 456 );
INSERT INTO `page_role` VALUES ( NULL, 'EDITOR', 12, 456 );
INSERT INTO `page_role` VALUES ( NULL, 'REVIEWER', 23, 456 );
INSERT INTO `page_role` VALUES ( NULL, 'WRITER', 34, 456 );

INSERT INTO `page` VALUES (567, 'Profile', 'Users can configure their personal information', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 567878, 567 );
INSERT INTO `page_role` VALUES ( NULL, 'EDITOR', 23, 567 );
INSERT INTO `page_role` VALUES ( NULL, 'REVIEWER', 34, 567 );
INSERT INTO `page_role` VALUES ( NULL, 'WRITER', 12, 567 );

-- Insert widgets
INSERT INTO `widget` (id, name, dtype, `text`, `order`, width, height, url, page_id) VALUES (123, 'head123', 'HEADING', 'Welcome', 0, NULL, NULL, NULL, 123);
INSERT INTO `widget` (id, name, dtype, `text`, `order`, width, height, url, page_id) VALUES (234, 'post234', 'HTML', '<p>Lorem</p>', 0, NULL, NULL, NULL, 234);
INSERT INTO `widget` (id, name, dtype, `text`, `order`, width, height, url, page_id) VALUES (345, 'head345', 'HEADING', 'Hi', 1, NULL, NULL, NULL, 345);
INSERT INTO `widget` (id, name, dtype, `text`, `order`, width, height, url, page_id) VALUES (456, 'intro456', 'HTML', '<h1>Hi</h1>', 2, NULL, NULL, NULL, 345);
INSERT INTO `widget` (id, name, dtype, `text`, `order`, width, height, url, page_id) VALUES (567, 'image345', 'IMAGE', NULL, 3, 50, 100, '/img/567.png', 345);
INSERT INTO `widget` (id, name, dtype, `text`, `order`, width, height, url, page_id) VALUES (678, 'video456', 'YOUTUBE', NULL, 0, 400, 300, 'https://youtu.be/h67VX51QXiQ', 456);

-- Insert addresses and phones
INSERT INTO `address` (street1, city, zip, `primary`, person_id) VALUES ('123 Adam St.', 'Alton', '01234', TRUE, 12);
INSERT INTO `address` (street1, city, zip, `primary`, person_id) VALUES ('234 Birch St.', 'Boston', '02345', FALSE, 12);
INSERT INTO `phone` (phone, `primary`, person_id) VALUES ('123-234-3456', TRUE, 12);
INSERT INTO `phone` (phone, `primary`, person_id) VALUES ('234-345-4566', FALSE, 12);

INSERT INTO `address` (street1, city, zip, `primary`, person_id) VALUES ('345 Charles St.', 'Chelms', '03455', TRUE, 23);
INSERT INTO `address` (street1, city, zip, `primary`, person_id) VALUES ('456 Down St.', 'Dalton', '04566', FALSE, 23);
INSERT INTO `address` (street1, city, zip, `primary`, person_id) VALUES ('543 East St.', 'Everett', '01112', FALSE, 23);
INSERT INTO `phone` (phone, `primary`, person_id) VALUES ('345-456-5677', TRUE, 23);

INSERT INTO `address` (street1, city, zip, `primary`, person_id) VALUES ('654 Frank St.', 'Foulton', '04322', TRUE, 34);
INSERT INTO `phone` (phone, `primary`, person_id) VALUES ('321-432-5435', TRUE, 34);
INSERT INTO `phone` (phone, `primary`, person_id) VALUES ('432-432-5433', FALSE, 34);
INSERT INTO `phone` (phone, `primary`, person_id) VALUES ('543-543-6544', FALSE, 34);
