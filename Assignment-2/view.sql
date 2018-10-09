USE `assignment_2`;
DROP VIEW IF EXISTS `developer_roles_and_privileges`;
CREATE VIEW `developer_roles_and_privileges` AS
	SELECT
		per.first_name AS first_name,
		per.last_name AS last_name,
    per.username AS username,
    per.email AS email,
    web.name AS website_name,
    web.visits AS website_visits,
    web.updated AS website_update_date,
    wr.role AS dev_web_role,
    wp.privilege AS dev_web_privilege,
    p.title AS page_title,
    p.views AS page_views,
    p.updated AS page_updated_date,
    pr.role AS dev_page_role,
    pp.privilege AS dev_page_privilege
	FROM person per, website web, page p, website_role wr, website_privilege wp, page_role pr, page_privilege pp
    WHERE
			per.id = web.developer_id AND
			web.id = p.website_id AND
	    wr.developer_id = per.id AND
	    wr.website_id = web.id AND
	    wp.developer_id = per.id AND
	    wp.website_id = web.id AND
	    pr.developer_id = per.id AND
	    pr.page_id = p.id AND
	    pp.developer_id = per.id AND
	    pp.page_id = p.id;
