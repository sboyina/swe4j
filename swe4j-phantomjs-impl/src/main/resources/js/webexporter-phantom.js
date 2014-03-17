
try{
	var page = require('webpage').create(),
		system = require('system'),
		logger = {
			log : function(msg){
				if(console){
					console.log(msg);
				}
			},
			logObject : function(object){
				var objectStrArr = [];
				for(key in object){
					objectStrArr.push(key + " : " + object[key]);
				}
				objectStrArr.push();
				logger.log('{'+objectStrArr.join(',')+'}');
			}
		},
		fnRender = function(url, outputFile, timeout, onSuccesscallBack, onErrorcallBack){
			page.open(url, function(status) {
				if (status === "success") {
					logger.log('Successfully loaded the url -' + url);
					window.setTimeout(function() {
							page.render(outputFile);
							logger.log('Successfully exported to -'+outputFile);
							onSuccesscallBack();
					}, timeout);
				} else {
					logger.log('Failed to load the url - '+url);
					onErrorcallBack();
				}
			});
		},	
		PDFGenerator = function(overrides){
			this.options = {
				'format' 			: 'A4',
				'orientation'		: 'portrait',
				'border'			: '1cm',
				'render-time'		: 3000
			};
			//extend default options
			for(key in this.options){
				if(overrides[key]){
					this.options[key] = overrides[key];
				}
			}
			this.render = function(url, outputFile, onSuccessCallBack, onErrorcallBack){
				var options = this.options;
				page.paperSize = { format: options['format'], 
									orientation: options['orientation'], 
									border: options['border'] };
				logger.log('PDF paper size : ');
				logger.logObject(page.paperSize);
				fnRender(url, outputFile, options['render-time'], onSuccessCallBack, onErrorcallBack);
			}
		},
		RasterImageGenerator = function(overrides){
			var options = {
				'render-time': 3000
			};
			this.render = function(url, outputFile, onSuccessCallBack, onErrorcallBack){
				fnRender(url, outputFile, options['render-time'], onSuccessCallBack, onErrorcallBack);
			};
		};
	//Parse system arguments 
	(function(system){
		var url = system.args[1],
			outputFile = system.args[2],
			optionStr = system.args[3],
			options = {},
			i = 0,
			generator,
			optionSplits;
			
		var optionStrSplits = optionStr.split(';');
		var len = optionStrSplits.length;
		for(i = 0; i < len; i++){
			optionSplits = optionStrSplits[i].split('=');
			options[optionSplits[0]] = optionSplits[1];
		}
		if(outputFile.substr(-4) == '.pdf'){
			generator = new PDFGenerator(options);
		} else {
			generator = new RasterImageGenerator(options);
		}
		generator.render(url, outputFile, function(){
			phantom.exit(0);
		}, function(){
			phantom.exit(-1);
		});
	})(system);
} catch(exception){
	throw exception;
	phantom.exit(-1);
}