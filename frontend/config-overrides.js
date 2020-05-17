const path = require('path');

module.exports = function override(config) {
  config.resolve = {
    ...config.resolve,
    alias: { '@components': path.resolve(__dirname, 'src/components/atoms') },
    alias: { '@components': path.resolve(__dirname, 'src/components/molecules') },
    alias: { '@components': path.resolve(__dirname, 'src/components/organisms') },
    alias: { '@components': path.resolve(__dirname, 'src/components/templates') },
    alias: { '@components': path.resolve(__dirname, 'src/components/pages') },
  };

  return config;
};
