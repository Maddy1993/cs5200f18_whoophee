USE `cs5200_fall2018_Niketh_RaghavSairam_jdbc`;
DROP PROCEDURE IF EXISTS `get_unanswered_questions`;
DROP PROCEDURE IF EXISTS `endorsed_user_for_week`;

DELIMITER //
-- Procedure definition for questions with most number of answers and no correct answer
CREATE DEFINER=CURRENT_USER PROCEDURE `get_unanswered_questions`(IN current_module VARCHAR(45))
BEGIN
    SELECT widget_additional.widget_name,tmp_5.question_content,tmp_5.answerid_count FROM (
        SELECT question_additional.widget_id,tmp_4.question_id,tmp_4.question_content,tmp_4.answerid_count FROM 
            question_additional JOIN (
                SELECT  tmp_3.question_id,tmp_3.question_content,count(tmp_3.answer_id) AS answerid_count FROM (
                    SELECT tmp_2.question_id,tmp_2.question_content,answer_additional.id AS answer_id FROM (
                        SELECT id AS question_id,question_content FROM (
                            SELECT id,question_content FROM question_additional WHERE module=current_module
                        ) tmp_1 
                        WHERE tmp_1.id not in (
                            SELECT question_id FROM answer_additional WHERE answer_accepted=true
                        )
                    ) tmp_2
                    JOIN answer_additional ON tmp_2.question_id=answer_additional.question_id
            ) tmp_3 GROUP BY tmp_3.question_id
            ORDER BY count(tmp_3.answer_id) DESC LIMIT 1
        ) tmp_4 
        ON question_additional.id=tmp_4.question_id
    ) tmp_5 JOIN widget_additional ON tmp_5.widget_id=widget_additional.id;
END//

-- Procedure definition for top 5 most endorsed users in a given duration
CREATE DEFINER=CURRENT_USER PROCEDURE `endorsed_user_for_week`(IN week_date DATE)
BEGIN
SELECT * FROM (
    SELECT person.first_name FROM (
        SELECT tmp_2.answered_by, count(tmp_2.question_id) FROM (
            SELECT answer_additional.answered_by, answer_additional.question_id FROM (
                SELECT * FROM question_additional WHERE question_additional.post_date BETWEEN week_date AND DATE_ADD(week_date, INTERVAL 1 WEEK)
            ) tmp_1 
            JOIN answer_additional ON tmp_1.id=answer_additional.question_id AND answer_additional.answer_accepted=true
        ) tmp_2 
        GROUP BY tmp_2.answered_by ORDER BY count(tmp_2.question_id) DESC LIMIT 5
    ) tmp_3 
    JOIN person ON person.id=tmp_3.answered_by
) tmp_5 
ORDER BY tmp_5.first_name;
   
END//

DELIMITER ;