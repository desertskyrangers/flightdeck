module.exports = {
	getCacheKey() {
		return 'css-transformer';
	},
	process() {
		return {
			code: 'module.exports = {};'
		};
	}
};