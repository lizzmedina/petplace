const dotenv = require('dotenv');

dotenv.config();

module.exports = {
    testEnvironment: 'jest-environment-jsdom',
    setupFiles: ['./jest.setup.js'],
    transform: {
        '^.+\\.(js|jsx|mjs)$': 'babel-jest',
    },
};
