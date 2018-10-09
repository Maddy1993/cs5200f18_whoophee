SET SQL_SAFE_UPDATES = 0;
USE `assignment_2`;

-- Problem 1
UPDATE phone JOIN person ON phone.person_id = person.id SET phone = '333-444-5555'
WHERE person.first_name = 'charlie' AND phone.primary = TRUE;


-- Problem 2
UPDATE widget SET `order` = `order`-1
where `order` between
	(SELECT `order` FROM (SELECT * FROM widget) AS w WHERE name = 'head345') and 3;
--
UPDATE widget SET `order` = 3 WHERE name = 'head345';


-- Problem 3
UPDATE page JOIN website ON page.website_id = website.id
SET page.title = CONCAT('CNET - ', page.title)
WHERE website.name = 'CNET';


-- Problem 4
DROP TABLE IF EXISTS tmp;
CREATE TEMPORARY TABLE tmp (first_name varchar(45), role varchar(8));

INSERT INTO tmp SELECT person.first_name, page_role.role FROM page_role
	JOIN developer ON page_role.developer_id = developer.id
	JOIN person ON person.id = developer.id
	JOIN `page` ON page_role.page_id = `page`.id WHERE person.first_name IN ('charlie', 'bob') AND
		page.title = 'CNET - Home';

UPDATE page_role
	JOIN developer ON page_role.developer_id = developer.id
	JOIN person ON person.id = developer.id
	JOIN `page` ON page_role.page_id = `page`.id
    JOIN tmp ON person.first_name <> tmp.first_name
    SET page_role.role = tmp.role WHERE person.first_name IN ('charlie', 'bob') AND
		page.title = 'CNET - Home';
