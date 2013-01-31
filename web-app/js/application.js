if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

$(function() {
	$("select#avatar-select").change(function () {
		console.log("SELECTED: " + $(this).val());
		var id = $(this).val();
		var baseUrl = $(this).attr("data-baseUrl");
		$("img#avatar-preview").attr("src", baseUrl + "/" + id);
	});
});