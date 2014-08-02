<?php
	class Data{
		private $name;
		private $value;
		
		public function __construct($name, $value){
			$this->name = $name;
			$this->value = $value;
		}
		
		public function getName(){
			return $this->name;
		}
		
		public function getValue(){
			return $this->value;
		}
	}

	class Item{
		private $datas;
		
		public function __construct() {
			$this->datas = array();
		}
		
		public function addData($data){
			array_push($this->datas, $data);
		}
		
		public function getDatas(){
			return $this->datas;
		}
	}

	abstract class Resource{
		private $name;
		public function __construct($name) { 
			$this->name = $name;
		}
		
		public function getName(){
			return $this->name;
		}
		abstract public function print_resource();
		
	}

	class Folder extends Resource{
		private $resources;
		
		public function __construct($name, $resources) {
			parent::__construct($name);	
			$this->resources = $resources;
		}
		
		public function getResources(){
			return $this->resources;
		}
		
		public function print_resource(){
			$output = '{';
			$output .= '"resource_type":"folder",';
			$output .= '	"name":"' . $this->getName().'",';
			$output .= '	"resources":[';
			for($i = 0; $i < count($this->getResources()); $i++){
				$output .= $this->getResources()[$i]->print_resource();
				if($i != count($this->getResources())-1)
					$output .= ',';
			}
			$output .= '	]';
			$output .= '}';
			return $output;
		}
	}

	class File extends Resource{
		private $date;
		
		public function __construct($name, $date) { 
			parent::__construct($name);
			$this->date = $date;
		}
		
		public function getResources(){
			return $this->resources;
		}
		
		
		public function getDate(){
			return $this->date;
		}
		
		public function print_Resource(){
			$output = '{';
			$output .= '"resource_type":"file",';
			$output .= '"name":"' . $this->getName() .'"';
			$output .= ',"date":"' . $this->date .'"';
			$output .= '}';
			return $output;
		}
	}

	function printItems($items){
		$output = '';
		for($i = 0;$i<count($items);$i++){
			$item = $items[$i];
			
			$output .= '{';
			for($j = 0;$j<count($item->getDatas());$j++){
				$data = $item->getDatas()[$j];
				$output .= '	"' .$data->getName() . '":"' . addslashes($data->getValue()) . '"';
				if( $j != count($item->getDatas())-1){
					$output .= ',';
				}			
			}
			$output .= '}';
			if( $i != count($items) -1){
				$output .= ',';
			}
		}
		return $output;
	}
	
	function format_string($row, $str){
		preg_match_all('/%(.*?)%/s', $str, $matches);
		$new_str = $str;
		foreach($matches[0] as $key => $res){
			$new_str = str_replace($res, $row($matches[1][$key]), $new_str);
		}
		return $new_str;
	}
	
	function printResources($resources){
		$output = '';
		
		for($i = 0;$i<count($resources);$i++){
			$resource = $resources[$i];
			$output .= $resource->print_resource();
			if( $i != count($resources)-1){
				$output .= ',';
			}
		}
		return $output;
	}

	function listResources($dir){
		$resources = array();
		$current_dir = opendir($dir) or die("Coulnt open directory");
		while($resource = @readdir($current_dir)) {
			if($resource != "." && $resource != ".."){
				if(is_dir($dir."/".$resource)&& $resource != "." && $resource != "..") {
					$res = listResources($dir."/".$resource);
					array_push($resources, new Folder($dir."/".$resource, $res));
				}
				else {
					array_push($resources, new File($resource, date ("F d Y H:i:s.", filemtime($resource)));
				}
			}
		}
		closedir($current_dir);
		return $resources;
	}
?>