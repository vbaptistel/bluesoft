var gulp = require('gulp'),
  sass = require('gulp-sass'),
  autoprefixer = require('gulp-autoprefixer'),
  minifycss = require('gulp-minify-css'),
  rename = require('gulp-rename'),
  connect = require('gulp-connect'),
  __dirname = 'src/main/resources/static/';

gulp.task('connect', function() {
  connect.server({
    root: __dirname,
    port: 4000,
    livereload: true,
    middleware: function (connect, opt) {
      var Proxy = require('http-proxy-middleware');

      var proxy = Proxy('/api', {
        target: 'http://localhost:8080',
        changeOrigin: true,
        logLevel: 'debug'
      });
      return [proxy];
    }
  });
});


gulp.task('sass', function() {
  gulp.src(__dirname + '**/*.scss')
    .pipe(sass())
    .pipe(gulp.dest(__dirname + 'css'))
    .pipe(connect.reload());
  
});

gulp.task('html', function() {
  gulp.src(__dirname + '**/*.html')
  .pipe(connect.reload());
});

gulp.task('js', function() {
  gulp.src(__dirname + '**/*.js')
  .pipe(connect.reload());
});

gulp.task('css', function() {
  gulp.src(__dirname + '**/*.css')
  .pipe(connect.reload());
});

gulp.task('watch', function() {
  gulp.watch(__dirname + '**/*.scss', ['sass']);
  gulp.watch(__dirname + '**/*.html', ['html']);
  gulp.watch(__dirname + '**/*.css', ['css']);
  gulp.watch(__dirname + '**/*.js', ['js']);
});

gulp.task('default', ['sass', 'connect', 'watch'], function() {

});