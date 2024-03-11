config.devServer = {
    //https://webpack.js.org/configuration/dev-server/#devserverhttps
    proxy: {
        '/CRDImages': {
            target: 'https://images.metmuseum.org',
            changeOrigin: true, // Important for CORS
            secure: false, // If you're proxying to a HTTPS domain, this may be needed
        },
    }, ...config.devServer, // merges any locally defined server options
}
