const { createDefaultPreset } = require("ts-jest");

const tsJestTransformCfg = createDefaultPreset().transform;

/** @type {import("jest").Config} **/
module.exports = {
	preset: 'ts-jest',
	setupFiles: ['./test/setupTests.tsx'],
  testEnvironment: "jsdom",
  transform: {
    ...tsJestTransformCfg,
		"\\.(css|less|scss)$": "./test/css-transformer.js"
  },
};
