USE `assignment_2`;

DROP TRIGGER IF EXISTS trigger_website_role_insert;
DROP TRIGGER IF EXISTS trigger_website_role_update;
DROP TRIGGER IF EXISTS trigger_website_role_delete;
DROP TRIGGER IF EXISTS trigger_page_role_insert;
DROP TRIGGER IF EXISTS trigger_page_role_update;
DROP TRIGGER IF EXISTS trigger_page_role_delete;

DELIMITER //

CREATE TRIGGER trigger_website_role_insert AFTER INSERT ON website_role FOR EACH ROW
BEGIN
	CASE NEW.role
		WHEN 'OWNER' THEN
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('CREATE', NEW.developer_id, NEW.website_id);
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('READ', NEW.developer_id, NEW.website_id);
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('UPDATE', NEW.developer_id, NEW.website_id);
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('DELETE', NEW.developer_id, NEW.website_id);
		WHEN 'ADMIN' THEN
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('CREATE', NEW.developer_id, NEW.website_id);
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('READ', NEW.developer_id, NEW.website_id);
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('UPDATE', NEW.developer_id, NEW.website_id);
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('DELETE', NEW.developer_id, NEW.website_id);
    WHEN 'WRITER' THEN
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('CREATE', NEW.developer_id, NEW.website_id);
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('READ', NEW.developer_id, NEW.website_id);
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('UPDATE', NEW.developer_id, NEW.website_id);
    WHEN 'EDITOR' THEN
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('READ', NEW.developer_id, NEW.website_id);
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('UPDATE', NEW.developer_id, NEW.website_id);
    ELSE
			INSERT INTO website_privilege(privilege, developer_id, website_id) VALUES ('READ', NEW.developer_id, NEW.website_id);
	END CASE;
END//

CREATE TRIGGER trigger_website_role_update AFTER UPDATE ON website_role FOR EACH ROW
BEGIN
	DELETE FROM website_privilege WHERE website_id = OLD.website_id AND developer_id = OLD.developer_id;
	CASE NEW.role
		WHEN 'OWNER' THEN
			INSERT into website_privilege(privilege,developer_id,website_id) value ('CREATE',NEW.developer_id,NEW.website_id);
			INSERT into website_privilege(privilege,developer_id,website_id) value ('READ',NEW.developer_id,NEW.website_id);
			INSERT into website_privilege(privilege,developer_id,website_id) value ('UPDATE',NEW.developer_id,NEW.website_id);
			INSERT into website_privilege(privilege,developer_id,website_id) value ('DELETE',NEW.developer_id,NEW.website_id);
		WHEN 'ADMIN' THEN
			INSERT into website_privilege(privilege,developer_id,website_id) value ('CREATE',NEW.developer_id,NEW.website_id);
			INSERT into website_privilege(privilege,developer_id,website_id) value ('READ',NEW.developer_id,NEW.website_id);
			INSERT into website_privilege(privilege,developer_id,website_id) value ('UPDATE',NEW.developer_id,NEW.website_id);
			INSERT into website_privilege(privilege,developer_id,website_id) value ('DELETE',NEW.developer_id,NEW.website_id);
		WHEN 'WRITER' THEN
	    INSERT into website_privilege(privilege,developer_id,website_id) value ('CREATE',NEW.developer_id,NEW.website_id);
	    INSERT into website_privilege(privilege,developer_id,website_id) value ('READ',NEW.developer_id,NEW.website_id);
	    INSERT into website_privilege(privilege,developer_id,website_id) value ('UPDATE',NEW.developer_id,NEW.website_id);
 		WHEN 'EDITOR' THEN
			INSERT into website_privilege(privilege,developer_id,website_id) value ('UPDATE',NEW.developer_id,NEW.website_id);
    	INSERT into website_privilege(privilege,developer_id,website_id) value ('READ',NEW.developer_id,NEW.website_id);
		ELSE
			INSERT into website_privilege(privilege,developer_id,website_id) value ('READ',NEW.developer_id,NEW.website_id);
	END CASE;
END//

CREATE TRIGGER trigger_website_role_delete AFTER DELETE ON website_role FOR EACH ROW
BEGIN
	DELETE FROM website_privilege WHERE website_id=OLD.website_id AND developer_id=OLD.developer_id;
END//

CREATE TRIGGER trigger_page_role_insert AFTER INSERT ON page_role FOR EACH ROW
BEGIN
	CASE NEW.role
		WHEN 'OWNER' THEN
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('CREATE', NEW.developer_id, NEW.page_id);
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('READ', NEW.developer_id, NEW.page_id);
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('UPDATE', NEW.developer_id, NEW.page_id);
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('DELETE', NEW.developer_id, NEW.page_id);
		WHEN 'ADMIN' THEN
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('CREATE', NEW.developer_id, NEW.page_id);
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('READ', NEW.developer_id, NEW.page_id);
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('UPDATE', NEW.developer_id, NEW.page_id);
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('DELETE', NEW.developer_id, NEW.page_id);
    WHEN 'WRITER' THEN
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('CREATE', NEW.developer_id, NEW.page_id);
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('READ', NEW.developer_id, NEW.page_id);
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('UPDATE', NEW.developer_id, NEW.page_id);
    WHEN 'EDITOR' THEN
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('READ', NEW.developer_id, NEW.page_id);
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('UPDATE', NEW.developer_id, NEW.page_id);
    ELSE
			INSERT INTO page_privilege(privilege, developer_id, page_id) VALUES ('READ', NEW.developer_id, NEW.page_id);
	END CASE;
END//

CREATE TRIGGER trigger_page_role_update AFTER UPDATE ON page_role FOR EACH ROW
BEGIN
	DELETE FROM page_privilege WHERE page_id = OLD.page_id AND developer_id = OLD.developer_id;
	CASE NEW.role
		WHEN 'OWNER' THEN
			INSERT into page_privilege(privilege,developer_id,page_id) value ('CREATE',NEW.developer_id,NEW.page_id);
			INSERT into page_privilege(privilege,developer_id,page_id) value ('READ',NEW.developer_id,NEW.page_id);
			INSERT into page_privilege(privilege,developer_id,page_id) value ('UPDATE',NEW.developer_id,NEW.page_id);
			INSERT into page_privilege(privilege,developer_id,page_id) value ('DELETE',NEW.developer_id,NEW.page_id);
		WHEN 'ADMIN' THEN
			INSERT into page_privilege(privilege,developer_id,page_id) value ('CREATE',NEW.developer_id,NEW.page_id);
			INSERT into page_privilege(privilege,developer_id,page_id) value ('READ',NEW.developer_id,NEW.page_id);
			INSERT into page_privilege(privilege,developer_id,page_id) value ('UPDATE',NEW.developer_id,NEW.page_id);
			INSERT into page_privilege(privilege,developer_id,page_id) value ('DELETE',NEW.developer_id,NEW.page_id);
		WHEN 'WRITER' THEN
	    INSERT into page_privilege(privilege,developer_id,page_id) value ('CREATE',NEW.developer_id,NEW.page_id);
	    INSERT into page_privilege(privilege,developer_id,page_id) value ('READ',NEW.developer_id,NEW.page_id);
	    INSERT into page_privilege(privilege,developer_id,page_id) value ('UPDATE',NEW.developer_id,NEW.page_id);
 		WHEN 'EDITOR' THEN
			INSERT into page_privilege(privilege,developer_id,page_id) value ('UPDATE',NEW.developer_id,NEW.page_id);
    	INSERT into page_privilege(privilege,developer_id,page_id) value ('READ',NEW.developer_id,NEW.page_id);
		ELSE
			INSERT into page_privilege(privilege,developer_id,page_id) value ('READ',NEW.developer_id,NEW.page_id);
	END CASE;
END//

CREATE TRIGGER trigger_page_role_delete AFTER DELETE ON page_role FOR EACH ROW
BEGIN
	DELETE FROM page_privilege WHERE page_id=OLD.page_id AND developer_id=OLD.developer_id;
END//

DELIMITER ;
