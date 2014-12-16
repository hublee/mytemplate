@var type = type!'';
@var value = value!'';
@var dictMode = dictMode!"showSelect";
@var width = width!'100%';
@var isValue = isValue!'yes'; //option是否是填充value,默认是,否则是填充id
@var isDefault = isDefault!"";

@if(dictMode == "showSelect"){ //显示下拉模式
<select class="chosen-select" name="${decode(isValue,"yes","type","id")}">
	@if(!isEmpty(isDefault)) {
	<option>全部</option>
	@}
	@for(item in getDictListByType(type)){
		@if(isValue == "yes"){
			<option value="${item.value!}" 
				${decode(value!,item.value,'selected','')}>
			${item.label!}
			</option>
		@}else{
			<option value="${item.id!}">
			${item.label!}
			</option>
		@}
	@}elsefor{}
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