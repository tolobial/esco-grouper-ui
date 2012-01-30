<%@include file="../_include.jsp"%>
<g:view stringsVar="msgs" locale="#{sessionController.locale}">
	<input type="image" name="ONLINE_HELP_GROUP_PROPERTIES_PRIVILEGES_URL" src="/<h:outputText value="#{sessionController.applicationContext}"/>/media/imgs/action/help.gif" title="<h:outputText value="#{msgs['ICON_HELP_TITLE']}" />" style="float:right;cursor:pointer;border:0;" />
	<!-- For all selected  -->
	<h:selectOneRadio id="privilegesRadio" styleClass="font_search"
					  value="#{groupPrivilegesController.defaultPrivilegesRadio.label}">
		<f:selectItems value="#{groupPrivilegesController.listPrivilegesRadio}" />
	</h:selectOneRadio>

	<h:selectOneRadio id="privilegesType" styleClass="font_search"
					  value="#{groupPrivilegesController.privilegesTypeRadio.label}">
		<f:selectItems value="#{groupPrivilegesController.listPrivilegesTypeRadio}" />
	</h:selectOneRadio>

	<h2>
		<h:outputText value="#{msgs['PRIVILEGES.OF.GROUP']}" />
		<h:outputText id="subtitleWarningPrivilege" styleClass="memberWarning" value="" />
	</h2>

	<!-- DYNAMIC CONTENT -->
	<div id="privilegesContent"></div>
	<script>

	$("input[value=PRIVILEGE_RADIO_IMMEDIATE]").parent().attr("title",Lang.getString("PRIVILEGE_RADIO_IMMEDIATE_TITLE"));
	$("input[value=PRIVILEGE_RADIO_EFFECTIVE]").parent().attr("title",Lang.getString("PRIVILEGE_RADIO_EFFECTIVE_TITLE"));
	$("input[value=PRIVILEGE_RADIO_ALL]").parent().attr("title",Lang.getString("PRIVILEGE_RADIO_ALL_TITLE"));

	$("input[value=PRIVILEGE_TYPE_RADIO_GROUP]").parent().attr("title",Lang.getString("GROUP_PRIVILEGE_ON_GROUP"));
	$("input[value=PRIVILEGE_TYPE_RADIO_FOLDER]").parent().attr("title",Lang.getString("GROUP_PRIVILEGE_ON_STEM"));

	_displayBlockUIOption = {
			onAfterShowBlockUI : function(){
				// First load
				var typeVal = $("input[name=privilegesType]:checked").val();
				if (typeVal == "PRIVILEGE_TYPE_RADIO_GROUP") {
					Core.pullAjaxContent("/" + Core.applicationContext + "/stylesheets/groupProperties/groupPropertiesPrivilegesGroup.jsf", {},"#privilegesContent", true, false);
				}
				else if (typeVal == "PRIVILEGE_TYPE_RADIO_FOLDER") {
					Core.pullAjaxContent("/" + Core.applicationContext + "/stylesheets/groupProperties/groupPropertiesPrivilegesStem.jsf", {},"#privilegesContent", true , false );
				}
		}
	};
	Core._showBlockUI(_displayBlockUIOption);

	// On radio click load
	Core.addAction($("input[name=privilegesType]"),
			Core.CLICK,
			function(e) {
				e.stopImmediatePropagation();
				_displayBlockUIOption = {
						onAfterShowBlockUI : function(){
								var typeValue = $("input[name=privilegesType]:checked").val();
								if (typeValue == "PRIVILEGE_TYPE_RADIO_GROUP") {
									Core.pullAjaxContent("/" + Core.applicationContext + "/stylesheets/groupProperties/groupPropertiesPrivilegesGroup.jsf", {},"#privilegesContent", true, false);
								}
								else if (typeValue == "PRIVILEGE_TYPE_RADIO_FOLDER") {
									Core.pullAjaxContent("/" + Core.applicationContext + "/stylesheets/groupProperties/groupPropertiesPrivilegesStem.jsf", {},"#privilegesContent", true ,false);
								}
					}
				};
				Core._showBlockUI(_displayBlockUIOption);

			},
			false
	);
	</script>
</g:view>