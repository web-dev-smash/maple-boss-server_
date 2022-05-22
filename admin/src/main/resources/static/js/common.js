$.fn.extend({
	initForm: function() {
		var arSelect = $(this).find("SELECT");
		$.each(arSelect, function(idx, element){
			var objEle = $(element);
			var eleAttr = objEle[0];
			if(eleAttr != "undefined"  && typeof(eleAttr) !== 'undefined') {
				if( eleAttr.tagName == "SELECT" ) {  // SELECT 초기화
					if(eleAttr.style.display == "none") {               
						if(eleAttr.multiple == true) {
							if(objEle.data("displayall") == false) 
								$("select[name='" + this.name + "']").multipleSelect("uncheckAll");
							else
								$("select[name='" + this.name + "']").multipleSelect("checkAll");      
						} else {
							$("select[name='" + this.name + "']").multipleSelect("setSelects", [""]);
						}
					} else {
						if ( eleAttr.defaultSelected )    objEle.prop("checked", true);
						else {
							var firstValue = objEle.find("option:eq(0)").val();
							if(firstValue == "")    objEle.val("");
							else    objEle.val(firstValue);
						}
					}
				}
			}
		});
		   
		$.each($(this)[0], function(idx, element){
			var objEle = $(element);
			var eleAttr = objEle[0];
			if(eleAttr != "undefined"  && typeof(eleAttr) !== 'undefined') {
				if( eleAttr.tagName == "INPUT" ) {  // INPUT 초기화
					
					if( (eleAttr.type == "text" || eleAttr.type == "password" || eleAttr.type == "number") && eleAttr.disabled == false && comm.getDataset(element, "type") != "date")    
					{
						objEle.val("");
					}
					else if( eleAttr.className != "multiSelect" 
						&& (eleAttr.type == "checkbox" || eleAttr.type =="radio")) {
						if( eleAttr.defaultChecked )    objEle.prop("checked", true);
						else    objEle.prop("checked", false);
					}                                                  
				} else if ( eleAttr.tagName == "TEXTAREA" ) {	// TEXTAREA 초기화
					objEle.val("");
				} 
			}
		});
		
		var arrInput = [];
		arrInput.push($(this).find("input"));
		arrInput.push($(this).find("textarea"));
		$.each(arrInput, function(idx, value) {
			$.each( value, function( idx, element ){  
				if(comm.getDataset(element, "type") == "date"){
					//console.log("달력 초기화 처리 안함");     
				}
			});
		});
		
		this.find(".vscomp-ele").each(function(){
			if(this.virtualSelect.multiple){
				this.toggleSelectAll(true);
			} else {
				this.setValue('');
			}
		});
	}
});