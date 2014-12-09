@var type = type!'';
@var value = value!'';
@var dictMode = dictMode!"showSelect";

@if(dictMode == "showSelect"){ //显示下拉模式
<select class="chosen-select" name="type">
	@for(item in dictFn(type)){
		<option value="${item.value!}" 
			${decode(value!,item.value,'selected','')}>
		${item.label!}
		</option>
	@}elsefor{}
</select>
@}else if(dictMode == "showName"){ //显示类型名称
	@for(item in dictFn(type)){
		@if(type == item.type && value == item.value){
			${item.label!}
			@break;
		@}
	@}elsefor{}
@}