
 $(document).ready(function() {	
 	//Logo me lleva a Home
 	$('.goHome').click(function(e){
		location.href= "/dashboard";
	});


 /*
 	//iscroll

	var myScroll;
	function loaded() {
		setTimeout(function () {
			myScroll = new iScroll('wrapper');
		}, 100);
	}
	window.addEventListener('load', loaded, false);

	var myScroll = new iScroll('wrapper', { hScrollbar: false, vScrollbar: false });
*/

	//NiceScroll
 	$("body").niceScroll();
 	$(".scroll").niceScroll();	
 
});