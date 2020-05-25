
String.prototype.isEmpty= function() {
	return (this.trim() == '');
}

$.fn.clearAndFocus = function(message) {
	alert(message);
	$(this).val('').focus();
}

String.prototype.equals = function(str) {
	return(this == str);
}