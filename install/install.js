var args = process.argv.slice(2);
var path = args[0];

var http = require('http');
var fs = require('fs');

var sha1sum;
var shagood;

var download = function(url, dest, cb, doChecksum) {
    var file = fs.createWriteStream(dest);
    var request = http.get(url, function(response) {
        response.pipe(file);
        file.on('finish', function() {
            file.close(cb);  // close() is async, call cb after close completes.
            console.log("Finished Download.")
            if(doChecksum)
            {
                if(getSha1(dest) == sha1sum)
                {
                    console.log(getSha1(dest));
                    console.log(sha1sum);
                    shagood = true;
                }
                else
                {
                    console.log(getSha1(dest));
                    console.log(sha1sum);
                    shagood = false;                        
                }
            } else {
                fs.readFile(path + "/AutoEval/sum", function(err, data) {
                sha1sum = data;
            });

            }
                
        });
    }).on('error', function(err) { // Handle errors
        fs.unlink(dest); // Delete the file async. (But we don't check the result)
        if (cb) cb(err.message);
    });
};

var getSha1 = function(input)
{
    var fs = require('fs');
    var crypto = require('crypto');
    
    fs.createReadStream(input).pipe(crypto.createHash('sha1').setEncoding('hex')).on('finish', function () {
        //console.log(this.read());
        console.log("Finished getSha1")
        return(this.read());        //Return the hash
    });
}

fs.mkdirSync(path + "/AutoEval/");

download("http://dl.rachlinski.cf/AutoEval/versions/beta/beta-1/hash/sum", path + "/AutoEval/sum", function(){      //Download the sum
    download("http://dl.rachlinski.cf/AutoEval/versions/beta/beta-1/AutoEval-Beta1.jar", path + "/AutoEval/AutoEval.jar", function(){
        console.log(shagood);
    }, true);      //Download the jar
}, false);

