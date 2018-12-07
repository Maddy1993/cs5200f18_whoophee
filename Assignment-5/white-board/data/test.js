require('./db')();
const assert = require('assert');

const universityDao = require('./daos/university.dao.server');

testStudentsInitialCount = () => {
    return universityDao.findAllStudents().then(students => {
        console.assert(students.length === 2, "students initial count must be 2.");
        console.log('Validated initial students count');
    });
};

testQuestionsInitialCount = () => {
    return universityDao.findAllQuestions().then(questions => {
        console.assert(questions.length === 4, "questions initial count must be 4");
        console.log('Validated initial questions count');
    })
};

testAnswersInitialCount = () => {
    return universityDao.findAllAnswers().then(answers => {
        console.assert(answers.length === 8, "answers initial count must be 8.");
        console.log('Validated initial answers count');
    });
};

testDeleteAnswer = () => {
    universityDao.deleteAnswer(890).then(() => {
        universityDao.findAllAnswers().then(answers => {
            console.assert(answers.length === 7, "answers count after deletion(890) must be 7");
            console.log('Validated answers count after deletion');
        });
        universityDao.findAnswersByStudent(234).then(answers => {
            console.assert(answers.length === 3, "answers count for Bob after deletion must be 3");
            console.log('Validated Bob\'s answers count after deletion');
        });
    });
};

testDeleteQuestion = () => {
    universityDao.deleteQuestion(321).then(() => {
        universityDao.findAllQuestions().then(questions => {
            console.assert(questions.length === 3, "questions count after deletion must be 3");
            console.log('Validated questions count after deletion');
        })
    })
};

testDeleteStudent = () => {
    universityDao.deleteStudent(234).then(() => {
        universityDao.findAllStudents().then(students => {
            console.assert(students.length === 1, "students count after deletion must be 1");
            console.log('Validated students count after deletion');
        })
    })
};

universityDao.truncateDatabase()
    .then(() => {
        console.log('Database truncated');
        universityDao.populateDatabase()
            .then(() => {
                console.log('Populated database')
                testStudentsInitialCount().then(() => {
                    testDeleteStudent();
                });
                testQuestionsInitialCount().then(() => {
                    testDeleteQuestion();
                });
                testAnswersInitialCount().then(() => {
                    testDeleteAnswer();
                });
            })
    }
);
