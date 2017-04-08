import Mock from "mockjs";



const usTopologyData = [
  {"name": "Rack8", "type": "node", "content": '{ "data": { "id": "Rack8" , "name": "Rack8\\nDev", "profile": {"name": "Rack8", "Description:": "The rack mainly for FortiADC developers."}} }' },
  {"name": "Rack12", "type":"node", "content": '{ "data": { "id": "Rack12", "name": "Rack12\\nPerformance", "profile": {"name": "Rack12", "Description:": "The rack for performance test."} } }' },
  {"name": "Rack13", "type":"node", "content": '{ "data": { "id": "Rack13", "name": "Rack13\\nPerformance", "profile":{"name": "Rack13", "Description:": "The rack for performance test."} } }'},
  {"name": "Rack15", "type": "node", "content":'{ "data": { "id": "Rack15", "name": "Rack15\\nPlatform", "profile":{"name": "Rack15", "Description:":"The rack for platform team."} } }'},
  {"name": "Rack16", "type": "node", "content": '{ "data": { "id": "Rack16", "name": "Rack16\\nQA", "profile":{"name":"Rack16", "Description:": "The rack mainly for FortiADC QA."} } }'},
  {"name":"R12_UP_SW", "type":"node", "content":'{ "data": { "id": "R12_UP_SW", "parent": "Rack12", "name": "UP_SW", "profile":{"name": "Rack12_UP_SW", "Description:":"Up link to the gateway."} }, "position": {"x":610, "y":10} }'},
  {"name":"R12_SW249", "type":"node", "content": '{ "data": { "id": "R12_SW249", "parent": "Rack12", "name":"SW249" ,"profile":{"name": "Rack12_SW249",  "mgmt_ip": "172.30.30.249", "http_port": 80, "https_port": 443}},"position": {"x":610, "y":50} }'},

  {"name":"R12_SW193", "type":"node", "content":'{ "data": { "id": "R12_SW193", "parent": "Rack12", "name":"SW193" ,"profile":{"name": "Rack12_SW193",  "mgmt_ip": "172.30.30.193", "http_port": 80, "https_port": 443}}, "position": {"x":610, "y":90} }'},
  {"name":"R12_TESTER252", "type":"node", "content":'{ "data": { "id": "R12_TESTER252", "parent": "Rack12", "name":"Tester252" }, "position": {"x":610, "y":130}, "classes": "device_tester" }'},
  {"name":"R12_TESTER250", "type":"node", "content":'{ "data": { "id": "R12_TESTER250", "parent": "Rack12", "name":"Tester250" }, "position": {"x":610, "y":170}, "classes": "device_tester" }'},
  {"name":"R12_TS254", "type":"node", "content":'{ "data": { "id": "R12_TS254", "parent": "Rack12", "name":"TS254" }, "position": {"x":610, "y":210}, "classes": "device_terminalserver" }'},
  {"name":"R13_UP_SW", "type":"node", "content":'{ "data": { "id": "R13_UP_SW", "parent": "Rack13", "name": "UP_SW", "profile":{"name": "Rack13_UP_SW", "Description:":"Up link to the gateway."} }, "position": {"x":810, "y":10} }'},
  {"name":"R15_UP_SW", "type":"node", "content":'{ "data": { "id": "R15_UP_SW", "parent": "Rack15", "name": "UP_SW", "profile":{"name": "Rack15_UP_SW", "Description:":"Up link to the gateway."} }, "position": {"x":1010, "y":10} }'},
  {"name":"R15_TS249", "type":"node", "content": '{ "data": { "id": "R15_TS249", "parent": "Rack15", "name": "TS249" }, "position": {"x":1010, "y":50}, "classes": "device_terminalserver" }'},
  {"name":"R16_UP_SW", "type":"node", "content":'{ "data": { "id": "R16_UP_SW", "parent": "Rack16", "name": "UP_SW", "profile":{"name": "Rack16_UP_SW", "Description:":"Up link to the gateway."} }, "position": {"x":1210, "y":10} }'},
  {"name":"R8_UP_SW", "type":"node", "content":'{ "data": { "id": "R8_UP_SW", "parent": "Rack8", "name":"UP_SW", "profile":{"name": "Rack8_UP_SW", "Description:":"Up link to the gateway.", "mgmt_ip": "172.30.154.250", "http_port": 80, "https_port": 443} },"position": {"x":1410, "y":10} }'},
  {"name":"R8_SW247", "type":"node", "content":'{ "data": { "id": "R8_SW247", "parent": "Rack8", "name":"SW247", "profile": {"name": "Rack8_SW247", "device_type": "FortiSwitch", "mgmt_ip": "172.30.154.247", "http_port": 80, "https_port": 443 }}, "position": {"x":1410, "y":50} }'},
  {"name":"R8_TS242", "type":"node", "content": '{ "data": { "id": "R8_TS242", "parent": "Rack8", "name":"TS242" }, "position": {"x":1410, "y":90}, "classes": "device_terminalserver" }'},
  {"name":"R8_VM188", "type":"node", "content":'{ "data": { "id": "R8_VM188", "parent": "Rack8", "name":"VM188" }, "position": {"x":1410, "y":130}, "classes": "device_vm" }'},
  {"name":"R8_VM243", "type":"node", "content":'{ "data": { "id": "R8_VM243", "parent": "Rack8", "name":"VM243" }, "position": {"x":1410, "y":170}, "classes": "device_vm" }'},
  {"name":"R8_VM244", "type":"node", "content":'{ "data": { "id": "R8_VM244", "parent": "Rack8", "name":"VM244" }, "position": {"x":1410, "y":210}, "classes": "device_vm" }'},
  {"name":"R8_VM246", "type":"node", "content":'{ "data": { "id": "R8_VM246", "parent": "Rack8", "name":"VM246" }, "position": {"x":1410, "y":250}, "classes": "device_vm" }'},
  {"name":"R13_SW179", "type":"node", "content":'{ "data": { "id": "R13_SW179", "parent": "Rack13", "name":"SW179", "profile":{"name": "Rack13_SW179",  "mgmt_ip": "172.30.30.179", "http_port": 80, "https_port": 443} }, "position": {"x":810, "y":50} }'},
  {"name":"R13_SW2", "type":"node", "content":'{ "data": { "id": "R13_SW2", "parent": "Rack13", "name":"SW2", "profile":{"name": "Rack13_SW2",  "mgmt_ip": "172.30.30.2", "http_port": 80, "https_port": 443} }, "position": {"x":810, "y":90} }'},
  {"name":"R13_ADC1", "type":"node", "content":'{ "data": { "id": "R13_ADC1", "parent": "Rack13", "name":"FortiADC1" }, "position": {"x":810, "y":130}, "classes": "device_adc" }'},
  {"name":"R13_ADC2", "type":"node", "content":'{ "data": { "id": "R13_ADC2", "parent": "Rack13", "name":"FortiADC2" }, "position": {"x":810, "y":170}, "classes": "device_adc" }'},
  {"name":"R16_SW250", "type":"node", "content":'{ "data": { "id": "R16_SW250", "parent": "Rack16", "name":"SW250", "profile": {"name": "Rack16_SW250", "device_type": "FortiSwitch", "mgmt_ip": "10.106.4.250", "http_port": 80, "https_port": 443 }}, "position": {"x":1210, "y":50} }'},
  {"name":"R16_GW196", "type":"node", "content":'{ "data": { "id": "R16_GW196", "parent": "Rack16", "name":"GW196", "profile": {"name": "Rack16_GW196", "device_type": "FortiADC", "mgmt_ip": "172.30.154.196", "http_port": 80, "https_port": 443, "console": "telnet 172.30.42.249 2055"} }, "position": {"x":1210, "y":90}, "classes": "device_gw"}'},
  {"name":"R16_VM245", "type":"node", "content":'{ "data": { "id": "R16_VM245", "parent": "Rack16", "name":"VM245" }, "position": {"x":1210, "y":130}, "classes": "device_vm" }'},
  {"name":"R16_VM240", "type":"node", "content":'{ "data": { "id": "R16_VM240", "parent": "Rack16", "name":"VM240", "profile":{"name": "Rack16_VM240", "device_type": "VMware ESXi Host", "mgmt_ip": "10.106.4.240", "vmware_web_console": "Yes", "username": "root", "password":"fortinet"} }, "position": {"x":1210, "y":170}, "classes": "device_vm" }'},
  {"name":"R16_VM241", "type":"node", "content":'{ "data": { "id": "R16_VM241", "parent": "Rack16", "name":"VM241" }, "position": {"x":1210, "y":210},  "classes": "device_vm"}'},
  {"name":"R16_VM242", "type":"node", "content":'{ "data": { "id": "R16_VM242", "parent": "Rack16", "name":"VM242" }, "position": {"x":1210, "y":250}, "classes": "device_vm" }'},
  {"name":"R16_VM200", "type":"node", "content":'{ "data": { "id": "R16_VM200", "parent": "Rack16", "name":"VM200" }, "position": {"x":1210, "y":290}, "classes": "device_vm" }'},
  {"name":"R16_AT41", "type":"node", "content":'{ "data": { "id": "R16_AT41", "parent": "Rack16", "name":"AutoTest41" }, "position": {"x":1210, "y":330}, "classes": "device_vm" }'},



  {"name":"SW249_to_SW179", "type":"edge", "content":'{ "data": { "id": "SW249_to_SW179", "source": "R12_SW249", "target": "R13_SW179", "label": "port31-32 <==> port49-50" } }'},
  {"name":"SW249_to_SW193", "type":"edge", "content":'{ "data": { "id": "SW249_to_SW193", "source": "R12_SW249", "target": "R12_SW193"} }'},
  {"name":"SW193_to_SW2", "type":"edge", "content":'{ "data": { "id": "SW193_to_SW2", "source": "R12_SW193", "target": "R13_SW2", "label": "port43 <==> uplink2" } }'},
  {"name":"TESTER252_to_SW193", "type":"edge", "content":'{ "data": { "id": "TESTER252_to_SW193", "source": "R12_SW193", "target": "R12_TESTER252"} }'},
  {"name":"TESTER250_to_SW193", "type":"edge", "content":'{ "data": { "id": "TESTER250_to_SW193", "source": "R12_SW193", "target": "R12_TESTER250"} }'},
  {"name":"SW179_to_SW250", "type":"edge", "content":'{ "data": { "id": "SW179_to_SW250", "source": "R13_SW179", "target": "R16_SW250", "label": "port47  <==> port47"} }'},
  {"name":"SW2_to_ADC1", "type":"edge", "content":'{ "data": { "id": "SW2_to_ADC1", "source": "R13_SW2", "target": "R13_ADC1", "label": ""} }'},
  {"name":"SW179_to_ADC2", "type":"edge", "content":'{ "data": { "id": "SW179_to_ADC2", "source": "R13_SW179", "target": "R13_ADC2", "label": ""} }'},
  {"name":"SW250_to_VM240", "type":"edge", "content":'{ "data": { "id": "SW250_to_VM240", "source": "R16_SW250", "target": "R16_VM240", "label": ""} }'},
  {"name":"SW250_to_VM241", "type":"edge", "content":'{ "data": { "id": "SW250_to_VM241", "source": "R16_SW250", "target": "R16_VM241", "label": ""} }'},
  {"name":"SW250_to_VM242", "type":"edge", "content":'{ "data": { "id": "SW250_to_VM242", "source": "R16_SW250", "target": "R16_VM242", "label": ""} }'},
  {"name":"SW250_to_VM200", "type":"edge", "content":'{ "data": { "id": "SW250_to_VM200", "source": "R16_SW250", "target": "R16_VM200", "label": ""} }'},
  {"name":"R16UPSW_to_R8UPSW", "type":"edge", "content":'{ "data": { "id": "R16UPSW_to_R8UPSW", "source": "R16_UP_SW", "target": "R8_UP_SW", "label": "port2 <==> port47"} }'},
  {"name":"R16GW196_to_R16UPSW", "type":"edge", "content":'{ "data": { "id": "R16GW196_to_R16UPSW", "source": "R16_GW196", "target": "R16_UP_SW", "label": "port2"} }'},


  {"name":"node_style", "type": "style", "content":'{ "selector": "node", "css": { "label": "data(name)", "text-valign": "center", "text-halign": "center", "background-color": "#6fb1fc", "shape": "roundrectangle", "width": "100", "text-wrap": "wrap"}}'},
  {"name":"subnode_style", "type": "style", "content":'{"selector": "$node > node", "css": { "padding-top": "10px", "padding-left": "10px", "padding-bottom": "10px", "padding-right": "10px", "text-valign": "top", "text-halign": "center", "background-color": "#eff5f5" }}'},
  {"name":"edge_style", "type": "style", "content":'{"selector": "edge", "css": { "curve-style": "bezier", "edge-text-rotation": "autorotate", "label": "data(label)", "line-color": "#ccc", "font-size": "8", "source-label": "data(sourcelabel)", "target-label": "data(targetlabel)", "text-rotation": "autorotate"}}'},
  {"name":"selected_style", "type": "style", "content":'{ "selector": ":selected", "css": { "background-color": "black", "line-color": "black", "target-arrow-color": "black", "source-arrow-color": "black"}}'},
  {"name":"device_vm_style", "type": "style", "content":'{ "selector": ".device_vm", "css": {"background-color": "#00ff00"}}'},
  {"name":"device_tester_style", "type": "style", "content":'{"selector": ".device_tester","css": {"background-color": "#ff0000"}}'},
  {"name":"device_adc_style", "type": "style", "content":'{"selector": ".device_adc","css": {"background-color": "#0000ff"}}'},
  {"name":"device_terminalserver_style", "type": "style", "content":'{"selector": ".device_terminalserver","css": {"background-color": "#cc00ff"}}'},
  {"name":"device_gw_style", "type": "style", "content":'{"selector": ".device_gw","css": {"background-color": "#336699"}}'},
  {"name":"TESTER252_to_SW193_style", "type": "style", "content":'{"selector": "#TESTER252_to_SW193","css":{"curve-style": "unbundled-bezier","control-point-distances": -80,"control-point-weights": 0.5}}'},
  {"name":"TESTER250_to_SW193_style", "type": "style", "content":'{"selector": "#TESTER250_to_SW193","css":{"curve-style": "unbundled-bezier","control-point-distances": 100,"control-point-weights": 0.5}}'},
  {"name":"SW179_to_SW250_style", "type": "style", "content":'{"selector": "#SW179_to_SW250","css":{"control-point-distances": -200,"control-point-weights": 0.5,"curve-style": "unbundled-bezier"}}'},
  {"name":"SW2_to_ADC1_style", "type": "style", "content":'{"selector": "#SW2_to_ADC1","css":{"curve-style": "unbundled-bezier","control-point-distances": -80,"control-point-weights": 0.5}}'},
  {"name":"SW179_to_ADC2_style", "type": "style", "content":'{"selector": "#SW179_to_ADC2","css":{"curve-style": "unbundled-bezier","control-point-distances": -120,"control-point-weights": 0.5}}'},
  {"name":"SW250_to_VM240_style", "type": "style", "content":'{"selector": "#SW250_to_VM240","css":{"curve-style": "unbundled-bezier","control-point-distances": -120,"control-point-weights": 0.5}}'},
  {"name":"SW250_to_VM241_style", "type": "style", "content":'{"selector": "#SW250_to_VM241","css":{"curve-style": "unbundled-bezier","control-point-distances": -160,"control-point-weights": 0.5}}'},
  {"name":"SW250_to_VM242_style", "type": "style", "content":'{"selector": "#SW250_to_VM242","css":{"curve-style": "unbundled-bezier","control-point-distances": -200,"control-point-weights": 0.5}}'},
  {"name":"SW250_to_VM200_style", "type": "style", "content":'{"selector": "#SW250_to_VM200","css":{"curve-style": "unbundled-bezier","control-point-distances": -230,"control-point-weights": 0.5}}'},
  {"name":"R16GW196_to_R16UPSW_style", "type": "style", "content":'{"selector": "#R16GW196_to_R16UPSW","css":{"curve-style": "unbundled-bezier","control-point-distances": 85,"control-point-weights": 0.5}}'},

];

const usTopologySettings = {};

export { usTopologySettings, usTopologyData };
