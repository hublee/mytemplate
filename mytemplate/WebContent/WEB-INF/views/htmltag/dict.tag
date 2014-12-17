@var type = type!'';
@var value = value!'';
@var dictMode = dictMode!"showSelect";
@var width = width!'100%';
@var isDefault = isDefault!"";
@var showType = showType!false;

@if(dictMode == "showSelect"){ //显示下拉模式
<select class="chosen-select" name="type">
	@if(!isEmpty(isDefault)) {
	<option value="">全部</option>
	@}
	@if(!showType){
		@for(item in getDictListByType(type)){
			<option value="${item.value!}" 
				${decode(value!,item.value,'selected','')}>
			${item.label!}
			</option>
		@}
	@}else{
		@for(item in getDictListByType(type,"group")){
			<option value="${item.type!}">
				${item.type!}
			</option>
		@}
	@}
</select>
@}else if(dictMode == "showName"){ //显示类型名称
	@for(item in getDictListByType(type)){
		@if(type == item.type && value == item.value){
			${item.label!}
			@break;
		@}
	@}elsefor{}
@}
<script type="text/javascript">
$(".chosen-select").chosen({width: "${width}"}); 
</script>