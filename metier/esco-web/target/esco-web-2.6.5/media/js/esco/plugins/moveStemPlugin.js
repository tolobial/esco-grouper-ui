var MoveStem=$.extend(true,{},CorePlugin,{_nodeHovered:null,_isNodeCut:false,_theNodeCut:null,_isInDragMode:false,_theNodeToMove:null,_previousNode:null,_canMoveIt:false,doOnFire:function(){this._entryPoint="MoveStemPlugin";
DragAndDrop.dragStartMoveStem=function(){if(MoveStem._theNodeCut!=null){$(MoveStem._theNodeCut).children("a").css("opacity","1");
MoveStem._theNodeCut=null;
MoveStem._isNodeCut=false
}MoveStem._isInDragMode=true;
_theNodeToMove=MoveStem._nodeHovered;
_theNodeToMove.children("a").css("opacity","0.4")
};
DragAndDrop.dragEndMoveStem=function(){MoveStem._isInDragMode=false;
_theNodeToMove.children("a").css("opacity","1");
_theNodeToMove=null;
if(MoveStem._previousNode!=null){if($.browser.msie){if($(MoveStem._previousNode).children("a").css("opacity")=="0.5"){MoveStem._canMoveIt=false
}else{MoveStem._canMoveIt=true
}$(MoveStem._previousNode).children("a").css("opacity","1")
}else{if($(MoveStem._previousNode).children("a").css("cursor")=="no-drop"){MoveStem._canMoveIt=false
}else{MoveStem._canMoveIt=true
}$(MoveStem._previousNode).children("a").css("cursor","pointer")
}}MoveStem._previousNode=null
};
DragAndDrop.moveStem=function(H,F,I,G){if($(F).attr("displayName")==$(H).attr("displayName")){return false
}if(!MoveStem._canMoveIt){return false
}MoveStem._canMoveIt=false;
var J={stemUuid:$(H).attr("id"),stemName:$(H).attr("name"),targetStemName:$(F).attr("name")};
MoveStem._resultOfMoveAction=null;
_displayBlockUIOption={onAfterShowBlockUI:function(){$.post("/"+Core.applicationContext+"/ajax/stemController/moveStem.jsf",J,function(A){if(Core.getStatus(A)){tree.settings.callback.onload=function(){TreePlugin.openAndSelectParent(Core.getValueOfXml(A,"message"));
$("li[typeNode=ROOT]").click().click();
Core._hideBlockUI();
tree.settings.callback.onload=function(){}
}
}tree.refresh($("li[id=:]"))
})
}};
Core._showBlockUI(_displayBlockUIOption);
return false
}
},init:function(B){TreeMenu.setCurrentCutAction(MoveStem);
MoveStem._isNodeCut=true;
MoveStem._theNodeCut=$(B);
$(B).children("a").css("opacity","0.4")
},release:function(){if(MoveStem._theNodeCut!=null){$(MoveStem._theNodeCut).children("a").css("opacity","1");
MoveStem._theNodeCut=null;
MoveStem._isNodeCut=false
}}});
var MoveStemPlugin=new DUI.Class(MoveStem,$.screen);