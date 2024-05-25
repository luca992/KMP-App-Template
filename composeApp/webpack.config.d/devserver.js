config.devServer = {
    ...config.devServer, // merges any locally defined server options
    proxy: [{
        context: ["/CRDImages"],
        target: 'https://images.metmuseum.org',
        changeOrigin: true, // Important for CORS
        secure: false, // If you're proxying a HTTPS domain, this may be needed
    }],
}
