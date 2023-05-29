// function myFunction(imgs) {
//     // Get the expanded image
//     var expandImg = document.getElementById("expandedImg");
//     console.log(expandImg);
//     // Get the image text
//     //var imgText = document.getElementById("imgtext");
//     // Use the same src in the expanded image as the image being clicked on from the grid
//     expandImg.parentElement.style.display = "none";
//     expandImg.src = imgs.src;
//     console.log(expandImg.src);
//     console.log(imgs.src);
//     // Use the value of the alt attribute of the clickable image as text inside the expanded image
//     //imgText.innerHTML = imgs.alt;
//     // Show the container element (hidden with CSS)
//     //expandImg.parentElement.style.display = "block";
//     expandImg.parentElement.style.display = "flex";
// }

function myFunction() {
    var x = document.getElementById("address-info");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }

    $('.collapse').collapse();
}

/* ---------------------------------------------- /*
         * Initialization General Scripts for all pages
         /* ---------------------------------------------- */

var homeSection = $('.home-section'),
    navbar      = $('.navbar-custom'),
    navHeight   = navbar.height(),
    worksgrid   = $('#works-grid'),
    width       = Math.max($(window).width(), window.innerWidth),
    mobileTest  = false;

if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
    mobileTest = true;
}

buildHomeSection(homeSection);
navbarAnimation(navbar, homeSection, navHeight);
navbarSubmenu(width);
hoverDropdown(width, mobileTest);

$(window).resize(function() {
    var width = Math.max($(window).width(), window.innerWidth);
    buildHomeSection(homeSection);
    hoverDropdown(width, mobileTest);
});

$(window).scroll(function() {
    effectsHomeSection(homeSection, this);
    navbarAnimation(navbar, homeSection, navHeight);
});

/* ---------------------------------------------- /*
 * Set sections backgrounds
 /* ---------------------------------------------- */

var module = $('.home-section, .module, .module-small, .side-image');
module.each(function(i) {
    if ($(this).attr('data-background')) {
        $(this).css('background-image', 'url(' + $(this).attr('data-background') + ')');
    }
});

/* ---------------------------------------------- /*
 * Home section height
 /* ---------------------------------------------- */

function buildHomeSection(homeSection) {
    if (homeSection.length > 0) {
        if (homeSection.hasClass('home-full-height')) {
            homeSection.height($(window).height());
        } else {
            homeSection.height($(window).height() * 0.85);
        }
    }
}


/* ---------------------------------------------- /*
 * Home section effects
 /* ---------------------------------------------- */

function effectsHomeSection(homeSection, scrollTopp) {
    if (homeSection.length > 0) {
        var homeSHeight = homeSection.height();
        var topScroll = $(document).scrollTop();
        if ((homeSection.hasClass('home-parallax')) && ($(scrollTopp).scrollTop() <= homeSHeight)) {
            homeSection.css('top', (topScroll * 0.55));
        }
        if (homeSection.hasClass('home-fade') && ($(scrollTopp).scrollTop() <= homeSHeight)) {
            var caption = $('.caption-content');
            caption.css('opacity', (1 - topScroll/homeSection.height() * 1));
        }
    }
}

/* ---------------------------------------------- /*
 * Intro slider setup
 /* ---------------------------------------------- */

if( $('.hero-slider').length > 0 ) {
    $('.hero-slider').flexslider( {
        animation: "fade",
        animationSpeed: 1000,
        animationLoop: true,
        prevText: '',
        nextText: '',
        before: function(slider) {
            $('.titan-caption').fadeOut().animate({top:'-80px'},{queue:false, easing: 'swing', duration: 700});
            slider.slides.eq(slider.currentSlide).delay(500);
            slider.slides.eq(slider.animatingTo).delay(500);
        },
        after: function(slider) {
            $('.titan-caption').fadeIn().animate({top:'0'},{queue:false, easing: 'swing', duration: 700});
        },
        useCSS: true
    });
}


/* ---------------------------------------------- /*
 * Rotate
 /* ---------------------------------------------- */

$(".rotate").textrotator({
    animation: "dissolve",
    separator: "|",
    speed: 3000
});


/* ---------------------------------------------- /*
 * Transparent navbar animation
 /* ---------------------------------------------- */

function navbarAnimation(navbar, homeSection, navHeight) {
    var topScroll = $(window).scrollTop();
    if (navbar.length > 0 && homeSection.length > 0) {
        if(topScroll >= navHeight) {
            navbar.removeClass('navbar-transparent');
        } else {
            navbar.addClass('navbar-transparent');
        }
    }
}

/* ---------------------------------------------- /*
 * Navbar submenu
 /* ---------------------------------------------- */

function navbarSubmenu(width) {
    if (width > 767) {
        $('.navbar-custom .navbar-nav > li.dropdown').hover(function() {
            var MenuLeftOffset  = $('.dropdown-menu', $(this)).offset().left;
            var Menu1LevelWidth = $('.dropdown-menu', $(this)).width();
            if (width - MenuLeftOffset < Menu1LevelWidth * 2) {
                $(this).children('.dropdown-menu').addClass('leftauto');
            } else {
                $(this).children('.dropdown-menu').removeClass('leftauto');
            }
            if ($('.dropdown', $(this)).length > 0) {
                var Menu2LevelWidth = $('.dropdown-menu', $(this)).width();
                if (width - MenuLeftOffset - Menu1LevelWidth < Menu2LevelWidth) {
                    $(this).children('.dropdown-menu').addClass('left-side');
                } else {
                    $(this).children('.dropdown-menu').removeClass('left-side');
                }
            }
        });
    }
}