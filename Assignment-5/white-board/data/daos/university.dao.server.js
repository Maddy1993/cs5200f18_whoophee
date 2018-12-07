const studentModel = require('../models/student.model.server');
const questionModel = require('../models/question.model.server');
const answerModel = require('../models/answer.model.server');

truncateDatabase = () => {
    return Promise.all([answerModel.remove(), questionModel.remove(), studentModel.remove()]);
};

populateDatabase = () => {
    let studentList = [
        {
            _id: 123, firstName: 'Alice',
            lastName: 'Wonderland',
            username: 'alice',
            password: 'alice',
            gradYear: 2020,
            scholarship: 15000
        }, 
        {
            _id: 234,
            firstName: 'Bob',
            lastName: 'Hope',
            username: 'bob',
            password: 'bob',
            gradYear: 2021,
            scholarship: 12000
        }
    ];
    let questionList = [
        {
            _id: 321,
            question: 'Is the following schema valid?',
            points: 10,
            questionType: 'TRUE_FALSE',
            trueFalse: {
                isTrue: false
            }
        },
        {
            _id: 432,
            question: 'DAO stands for Dynamic Access Object.',
            points: 10,
            questionType: 'TRUE_FALSE',
            trueFalse: {
                isTrue: false
            }
        }, 
        {
            _id: 543,
            question: 'What does JPA stand for?',
            points: 10,
            questionType: 'MULTIPLE_CHOICE',
            multipleChoice: {
                choices: 'Java Persistence API,Java Persisted Application,JavaScript Persistence API,JSON Persistent Associations',
                correct: 1
            }
        },
        {
            _id: 654,
            question: 'What does ORM stand for?',
            points: 10,
            questionType: 'MULTIPLE_CHOICE',
            multipleChoice: {
                choices: 'Object Relational Model,Object Relative Markup,Object Reflexive Model,Object Relational Mapping',
                correct: 4
            }
        }
    ];
    let answerList = [
        {
            _id: 123,
            trueFalseAnswer: true,
            student: 123,
            question: 321
        },
        {
            _id: 234,
            trueFalseAnswer: true,
            student: 123,
            question: 432
        },
        {
            _id: 345,
            multipleChoiceAnswer: 1,
            student: 123,
            question: 543
        },
        {
            _id: 456,
            multipleChoiceAnswer: 2,
            student: 123,
            question: 654
        },
        {        
            _id: 567,
            trueFalseAnswer: true,
            student: 234,
            question: 321
        },
        {
            _id: 678,
            trueFalseAnswer: true,
            student: 234,
            question: 432
        },
        {
            _id: 789,
            multipleChoiceAnswer: 3,
            student: 234,
            question: 543
        },
        {
            _id: 890,
            multipleChoiceAnswer: 4,
            student: 234,
            question: 654
        }
    ];
    return Promise.all([studentModel.insertMany(studentList), questionModel.insertMany(questionList), answerModel.insertMany(answerList)]);
};

createStudent = student =>
    studentModel.create(student);

deleteStudent = studentId =>
    studentModel.remove({_id: studentId});

createQuestion = question =>
    questionModel.create(question);

deleteQuestion = questionId =>
    questionModel.remove({_id: questionId});

createAnswer = answer =>
    answerModel.create(answer);

deleteAnswer = answerId =>
    answerModel.remove({_id:answerId});

findAllStudents = () =>
    studentModel.find();

findStudentById = studentId =>
    studentModel.findById(studentId);

findAllQuestions = () =>
    questionModel.find();

findQuestionById = questionId =>
    questionModel.findById(questionId);

findAllAnswers = () =>
    answerModel.find();

findAnswerById = answerId =>
    answerModel.findById(answerId);

findAnswersByStudent = studentId =>
    answerModel.find({student: studentId});

findAnswersByQuestion = questionId =>
    answerModel.find({question: questionId});

module.exports = {
    populateDatabase,
    truncateDatabase,
    createStudent,
    deleteStudent,
    createQuestion,
    deleteQuestion,
    createAnswer,
    deleteAnswer,
    findAllStudents,
    findStudentById,
    findAllQuestions,
    findQuestionById,
    findAllAnswers,
    findAnswerById,
    findAnswersByStudent,
    findAnswersByQuestion
};