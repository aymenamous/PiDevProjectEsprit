
(function (document) {
    $('.carousel').carousel();
                
    'use strict';

    // Grab a reference to our auto-binding template
    // and give it some initial binding values
    // Learn more about auto-binding templates at http://goo.gl/Dx1u2g

    document.addEventListener('WebComponentsReady', function () {
        var template = document.querySelector('neon-animated-pages');
        template.selected = "AllIdeas";
    });

    document.addEventListener('changePage', function (data) {
        
        document.querySelector("neon-animated-pages").selected = (data.detail == "showcase") ? "showcase" : "AllIdeas";
    })

    document.addEventListener('updateShowcase', function (data) {
        
        document.querySelector('portfolio-showcase').product = data.detail;
    })
    
    document.addEventListener('changePageMesIdees', function (data) {
        
        document.querySelector("neon-animated-pages").selected = (data.detail == "showcaseMesIdees") ? "showcaseMesIdees" : "AllIdeas";
        
    })

    document.addEventListener('updateShowcaseMesIdees', function (data) {
        
        document.querySelector('portfolio-showcaseMesIdees').product = data.detail;
        
    })

})(document);
