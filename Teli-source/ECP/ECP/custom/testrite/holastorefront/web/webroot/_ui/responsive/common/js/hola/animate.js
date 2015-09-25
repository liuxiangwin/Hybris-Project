// JavaScript Document
function animate(){	
//mobileNav
$('.mobileMenu').find('a').click(function(){
		$('#mobileNav').toggle();
	})
}

function brandTab(){
	$('.brandTab li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	});
	}


//初始動畫
$(function(){
	animate();
})