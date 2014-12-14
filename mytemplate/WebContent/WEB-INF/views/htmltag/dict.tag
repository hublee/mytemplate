@var type = type!'';
@var value = value!'';
@var dictMode = dictMode!"showSelect";
@var width = width!'100%';

@if(dictMode == "showSelect"){ //显示下拉模式
<select class="chosen-select" name="type">
	@for(item in getDictListByType(type)){
		<option value="${item.value!}" 
			${decode(value!,item.value,'selected','')}>
		${item.label!}
		</option>
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