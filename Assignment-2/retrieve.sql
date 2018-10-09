use `assignment_2`;
-- Problem 1a
SELECT person.*
FROM developer JOIN person ON person.id = developer.id;

-- Problem 1b
SELECT person.*
FROM developer JOIN person ON person.id = developer.id
WHERE developer.id = 34;

-- Problem 1c
SELECT person.*
FROM developer
	JOIN website_role ON developer.id = website_role.developer_id
  JOIN person ON developer.id = person.id
  JOIN website ON website_role.website_id = website.id
WHERE website.name = 'Twitter' AND
	role <> 'OWNER';

-- Problem 1d
SELECT person.*
FROM developer JOIN page_role ON developer.id = page_role.developer_id
	JOIN page ON page.id = page_role.page_id
  JOIN person ON developer.id = person.id
WHERE role = 'REVIEWER' AND page.views < 300000;

-- Problem 1e
SELECT person.*
FROM page
	JOIN widget ON widget.page_id = page.id
  JOIN website ON page.website_id = website.id
  JOIN page_role ON page.id = page_role.page_id
  JOIN developer ON page_role.developer_id = developer.id
  JOIN person ON developer.id = person.id
WHERE page_role.role = 'WRITER' AND website.name = 'CNET' AND widget.dtype = 'HEADING';


-- Problem 2a
SELECT *, MIN(visits)
FROM website;

-- Problem 2b
SELECT name FROM website WHERE id = 678;

-- Problem 2c
SELECT website.*
FROM website
	JOIN page ON page.website_id = website.id
  JOIN widget ON widget.page_id = page.id
  JOIN page_role ON page_role.page_id = page.id
  JOIN developer ON page_role.developer_id = developer.id
  JOIN person ON person.id = developer.id
WHERE widget.dtype = 'YOUTUBE' AND page_role.role = 'REVIEWER' AND person.first_name = 'bob';

-- Problem 2d
SELECT website.*
FROM website
	JOIN website_role ON website_role.website_id = website.id
  JOIN developer ON website_role.developer_id = developer.id
  JOIN person ON developer.id = person.id
WHERE website_role.role = 'OWNER' AND person.first_name = 'alice';

-- Problem 2e
SELECT website.*
FROM website
	JOIN website_role ON website_role.website_id = website.id
  JOIN developer ON website_role.developer_id = developer.id
  JOIN person ON developer.id = person.id
WHERE website_role.role = 'ADMIN' AND person.first_name = 'charlie' AND website.visits > 6000000;


-- Problem 3a
SELECT *, MAX(views) FROM page;

-- Problem 3b
SELECT title FROM page WHERE id = 234;

-- Problem 3c
SELECT page.*
FROM page
	JOIN page_role ON page_role.page_id = page.id
  JOIN developer on page_role.developer_id = developer.id
  JOIN person ON person.id = developer.id
WHERE person.first_name = 'alice' AND page_role.role = 'EDITOR';

-- Problem 3d
SELECT SUM(page.views)
FROM page
	JOIN website ON page.website_id = website.id
WHERE website.name = 'CNET';

-- Problem 3e
SELECT AVG(page.views)
FROM page
	JOIN website ON page.website_id = website.id
WHERE website.name = 'Wikipedia';

-- Problem 4a
SELECT widget.*
FROM widget
	JOIN page ON widget.page_id = page.id
  JOIN website ON page.website_id = website.id
WHERE website.name='CNET';

-- Problem 4b
SELECT widget.*
FROM widget
	JOIN page ON widget.page_id = page.id
  JOIN website ON page.website_id = website.id
WHERE website.name='CNN' AND
	widget.dtype = 'YOUTUBE';

-- Problem 4c
SELECT widget.*
FROM widget
	JOIN page ON widget.page_id = page.id
  JOIN website ON page.website_id = website.id
  JOIN page_role ON page_role.page_id = page.id
  JOIN developer ON developer.id = page_role.developer_id
  JOIN person ON developer.id = person.id
WHERE widget.dtype = 'IMAGE' AND page_role.role = 'REVIEWER' AND person.first_name = 'alice';

-- Problem 4d
SELECT COUNT(widget.id)
FROM widget
	JOIN page ON widget.page_id = page.id
  JOIN website ON page.website_id = website.id
WHERE website.name = 'Wikipedia';

-- Problem 5a
SELECT website.name
FROM website
	JOIN website_privilege ON website_privilege.website_id = website.id
  JOIN developer ON developer.id = website_privilege.developer_id
  JOIN person ON developer.id = person.id
WHERE person.first_name = 'bob' AND website_privilege.privilege = 'DELETE';

-- Problem 5b
SELECT page.title
FROM page
	JOIN page_privilege ON page_privilege.page_id = page.id
  JOIN developer ON developer.id = page_privilege.developer_id
  JOIN person ON developer.id = person.id
WHERE person.first_name = 'charlie' AND page_privilege.privilege = 'CREATE';
