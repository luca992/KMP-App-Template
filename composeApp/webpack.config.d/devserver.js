config.devServer = {
    ...config.devServer, // merges any locally defined server options
    proxy: {
        '/CRDImages': {
            target: 'https://images.metmuseum.org',
            changeOrigin: true, // Important for CORS
            secure: false, // If you're proxying to a HTTPS domain, this may be needed
        },
    },
}
