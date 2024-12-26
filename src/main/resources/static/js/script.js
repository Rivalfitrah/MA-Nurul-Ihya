$(".outline").on("click", function(){
  if($(".menu").hasClass("active")){
     $(".menu").removeClass("active");
     $(this).find("a").html("<ion-icon name='menu-outline'></ion-icon>");
  } else {
      $(".menu").addClass("active");
      $(this).find("a").html("<ion-icon name='close-outline'></ion-icon>");
  }
})


// admin
$(function(){
    $(".admin-login a").click(function(e){
        e.preventDefault(); // Mencegah tindakan default dari tautan

        // Menampilkan notifikasi "Apakah Anda admin?"
        var isAdmin = confirm("Apakah Anda admin?");
        
        // Jika pengguna adalah admin, maka arahkan ke halaman login admin
        if(isAdmin){
            window.location.href = "/login-admin";
        }
    });
});

// transisi sejarah
document.addEventListener('DOMContentLoaded', function() {
  const container = document.getElementById('container');
  container.classList.add('show');
});

// transisi visi misi
document.addEventListener('DOMContentLoaded', function() {
  const container = document.querySelector('.container');
  container.classList.add('show');
});

// slider berjalan otomatis
document.addEventListener('DOMContentLoaded', function() {
  var myCarousel = document.querySelector('#carouselExampleDark');
  var carousel = new bootstrap.Carousel(myCarousel, {
      interval: 3000, // Default interval for the rest of the slides
      ride: 'carousel'
  });
});


$(".menu > li > a").on("click", function(e) {
  if ($(this).next(".submenu").length > 0) {
      e.preventDefault();
      // Sisipkan logika lain di sini jika diperlukan
  }
});

