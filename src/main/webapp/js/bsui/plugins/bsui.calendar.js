/**
 * dhcc-bsui-1.5.3.4
 * Component:calendar
 */(function(d){function v(a,b){var n=d.data(a,"calendar").options,k=d(a);b&&d.extend(n,{width:b.width,height:b.height});k._size(n,k.parent());k.find(".calendar-body")._outerHeight(k.height()-k.find(".calendar-header")._outerHeight());k.find(".calendar-menu").is(":visible")&&w(a)}function y(a){d(a).addClass("calendar").html('\x3cdiv class\x3d"calendar-header"\x3e\x3cdiv class\x3d"calendar-nav calendar-prevmonth"\x3e\x3c/div\x3e\x3cdiv class\x3d"calendar-nav calendar-nextmonth"\x3e\x3c/div\x3e\x3cdiv class\x3d"calendar-nav calendar-prevyear"\x3e\x3c/div\x3e\x3cdiv class\x3d"calendar-nav calendar-nextyear"\x3e\x3c/div\x3e\x3cdiv class\x3d"calendar-title"\x3e\x3cspan class\x3d"calendar-text"\x3e\x3c/span\x3e\x3c/div\x3e\x3c/div\x3e\x3cdiv class\x3d"calendar-body"\x3e\x3cdiv class\x3d"calendar-menu"\x3e\x3cdiv class\x3d"calendar-menu-year-inner"\x3e\x3cspan class\x3d"calendar-nav calendar-menu-prev"\x3e\x3c/span\x3e\x3cspan\x3e\x3cinput class\x3d"calendar-menu-year" type\x3d"text"\x3e\x3c/input\x3e\x3c/span\x3e\x3cspan class\x3d"calendar-nav calendar-menu-next"\x3e\x3c/span\x3e\x3c/div\x3e\x3cdiv class\x3d"calendar-menu-month-inner"\x3e\x3c/div\x3e\x3c/div\x3e\x3c/div\x3e');d(a).bind("_resize",function(b,n){(d(this).hasClass("bsui-fluid")||n)&&v(a);return!1})}function z(a){function b(a){var b=d(a).closest(".calendar-day");return b.length?b:d(a)}function n(b){var c=d(a).find(".calendar-menu"),q=c.find(".calendar-menu-year").val(),e=c.find(".calendar-selected").attr("abbr");isNaN(q)||(f.year=parseInt(q),f.month=parseInt(e),u(a));b&&c.hide()}function k(b){f.year+=b;u(a);r.find(".calendar-menu-year").val(f.year)}function t(b){f.month+=b;12<f.month?(f.year++,f.month=1):1>f.month&&(f.year--,f.month=12);u(a);r.find("td.calendar-selected").removeClass("calendar-selected");r.find("td:eq("+(f.month-1)+")").addClass("calendar-selected")}var f=d.data(a,"calendar").options,r=d(a).find(".calendar-menu");r.find(".calendar-menu-year").unbind(".calendar").bind("keypress.calendar",function(a){13==a.keyCode&&n(!0)});d(a).unbind(".calendar").bind("mouseover.calendar",function(a){a=b(a.target);(a.hasClass("calendar-nav")||a.hasClass("calendar-text")||a.hasClass("calendar-day")&&!a.hasClass("calendar-disabled"))&&a.addClass("calendar-nav-hover")}).bind("mouseout.calendar",function(a){a=b(a.target);(a.hasClass("calendar-nav")||a.hasClass("calendar-text")||a.hasClass("calendar-day")&&!a.hasClass("calendar-disabled"))&&a.removeClass("calendar-nav-hover")}).bind("click.calendar",function(d){var c=b(d.target);if(c.hasClass("calendar-menu-next")||c.hasClass("calendar-nextyear"))k(1);else if(c.hasClass("calendar-menu-prev")||c.hasClass("calendar-prevyear"))k(-1);else if(c.hasClass("calendar-menu-month"))r.find(".calendar-selected").removeClass("calendar-selected"),c.addClass("calendar-selected"),n(!0);else if(c.hasClass("calendar-prevmonth"))t(-1);else if(c.hasClass("calendar-nextmonth"))t(1);else if(c.hasClass("calendar-text"))r.is(":visible")?r.hide():w(a);else if(c.hasClass("calendar-day")&&!c.hasClass("calendar-disabled")){d=f.current;c.closest("div.calendar-body").find(".calendar-selected").removeClass("calendar-selected");c.addClass("calendar-selected");var l=c.attr("abbr").split(","),c=parseInt(l[0]),e=parseInt(l[1]),l=parseInt(l[2]);f.current=new Date(c,e-1,l);f.onSelect.call(a,f.current);d&&d.getTime()==f.current.getTime()||f.onChange.call(a,f.current,d);if(f.year!=c||f.month!=e)f.year=c,f.month=e,u(a)}})}function w(a){var b=d.data(a,"calendar").options;d(a).find(".calendar-menu").show();if(d(a).find(".calendar-menu-month-inner").is(":empty")){d(a).find(".calendar-menu-month-inner").empty();for(var n=d('\x3ctable class\x3d"calendar-mtable"\x3e\x3c/table\x3e').appendTo(d(a).find(".calendar-menu-month-inner")),k=0,t=0;3>t;t++)for(var f=d("\x3ctr\x3e\x3c/tr\x3e").appendTo(n),r=0;4>r;r++)d('\x3ctd class\x3d"calendar-nav calendar-menu-month"\x3e\x3c/td\x3e').html(b.months[k++]).attr("abbr",k).appendTo(f)}n=d(a).find(".calendar-body");a=d(a).find(".calendar-menu");k=a.find(".calendar-menu-year-inner");t=a.find(".calendar-menu-month-inner");k.find("input").val(b.year).focus();t.find("td.calendar-selected").removeClass("calendar-selected");t.find("td:eq("+(b.month-1)+")").addClass("calendar-selected");a._outerWidth(n._outerWidth());a._outerHeight(n._outerHeight());t._outerHeight(a.height()-k._outerHeight())}function u(a){var b=d.data(a,"calendar").options;b.current&&!b.validator.call(a,b.current)&&(b.current=null);var n=new Date,n=n.getFullYear()+","+(n.getMonth()+1)+","+n.getDate(),k=b.current?b.current.getFullYear()+","+(b.current.getMonth()+1)+","+b.current.getDate():"",t=6-b.firstDay,f=t+1;7<=t&&(t-=7);7<=f&&(f-=7);d(a).find(".calendar-title span").html(b.months[b.month-1]+" "+b.year);var r=d(a).find("div.calendar-body");r.children("table").remove();var q=['\x3ctable class\x3d"calendar-dtable" cellspacing\x3d"0" cellpadding\x3d"0" border\x3d"0"\x3e'];q.push("\x3cthead\x3e\x3ctr\x3e");b.showWeek&&q.push('\x3cth class\x3d"calendar-week"\x3e'+b.weekNumberHeader+"\x3c/th\x3e");for(var c=b.firstDay;c<b.weeks.length;c++)q.push("\x3cth\x3e"+b.weeks[c]+"\x3c/th\x3e");for(c=0;c<b.firstDay;c++)q.push("\x3cth\x3e"+b.weeks[c]+"\x3c/th\x3e");q.push("\x3c/tr\x3e\x3c/thead\x3e");q.push("\x3ctbody\x3e");for(var l=b.year,e=b.month,c=d.data(a,"calendar").options,h=[],p=(new Date(l,e,0)).getDate(),g=1;g<=p;g++)h.push([l,e,g]);l=[];p=[];for(g=-1;0<h.length;)e=h.shift(),p.push(e),e=(new Date(e[0],e[1]-1,e[2])).getDay(),g==e?e=0:e==(0==c.firstDay?7:c.firstDay)-1&&(l.push(p),p=[]),g=e;p.length&&l.push(p);h=l[0];if(7>h.length)for(;7>h.length;)c=h[0],e=new Date(c[0],c[1]-1,c[2]-1),h.unshift([e.getFullYear(),e.getMonth()+1,e.getDate()]);else{c=h[0];p=[];for(g=1;7>=g;g++)e=new Date(c[0],c[1]-1,c[2]-g),p.unshift([e.getFullYear(),e.getMonth()+1,e.getDate()]);l.unshift(p)}for(h=l[l.length-1];7>h.length;)c=h[h.length-1],e=new Date(c[0],c[1]-1,c[2]+1),h.push([e.getFullYear(),e.getMonth()+1,e.getDate()]);if(6>l.length){c=h[h.length-1];p=[];for(g=1;7>=g;g++)e=new Date(c[0],c[1]-1,c[2]+g),p.push([e.getFullYear(),e.getMonth()+1,e.getDate()]);l.push(p)}for(c=0;c<l.length;c++){var h=l[c],m="";0==c?m="calendar-first":c==l.length-1&&(m="calendar-last");q.push('\x3ctr class\x3d"'+m+'"\x3e');b.showWeek&&(g=b.getWeekNumber(new Date(h[0][0],parseInt(h[0][1])-1,h[0][2])),q.push('\x3ctd class\x3d"calendar-week"\x3e'+g+"\x3c/td\x3e"));for(g=0;g<h.length;g++){var e=h[g],p=e[0]+","+e[1]+","+e[2],u=new Date(e[0],parseInt(e[1])-1,e[2]),v=b.formatter.call(a,u),m=b.styler.call(a,u),w="",x="";"string"==typeof m?x=m:m&&(w=m["class"]||"",x=m.style||"");m="calendar-day";if(b.year!=e[0]||b.month!=e[1])m+=" calendar-other-month";p==n&&(m+=" calendar-today");p==k&&(m+=" calendar-selected");g==t?m+=" calendar-saturday":g==f&&(m+=" calendar-sunday");0==g?m+=" calendar-first":g==h.length-1&&(m+=" calendar-last");m+=" "+w;b.validator.call(a,u)||(m+=" calendar-disabled");q.push('\x3ctd class\x3d"'+m+'" abbr\x3d"'+p+'" style\x3d"'+x+'"\x3e'+v+"\x3c/td\x3e")}q.push("\x3c/tr\x3e")}q.push("\x3c/tbody\x3e");q.push("\x3c/table\x3e");r.append(q.join(""));r.children("table.calendar-dtable").prependTo(r);b.onNavigate.call(a,b.year,b.month)}d.fn.calendar=function(a,b){if("string"==typeof a)return d.fn.calendar.methods[a](this,b);a=a||{};return this.each(function(){var b=d.data(this,"calendar");b?d.extend(b.options,a):(b=d.data(this,"calendar",{options:d.extend({},d.fn.calendar.defaults,d.fn.calendar.parseOptions(this),a)}),y(this));0==b.options.border&&d(this).addClass("calendar-noborder");v(this);z(this);u(this);d(this).find("div.calendar-menu").hide()})};d.fn.calendar.methods={options:function(a){return d.data(a[0],"calendar").options},resize:function(a,b){return a.each(function(){v(this,b)})},moveTo:function(a,b){return a.each(function(){if(b){var a=d(this).calendar("options");if(a.validator.call(this,b)){var k=a.current;d(this).calendar({year:b.getFullYear(),month:b.getMonth()+1,current:b});k&&k.getTime()==b.getTime()||a.onChange.call(this,a.current,k)}}else a=new Date,d(this).calendar({year:a.getFullYear(),month:a.getMonth()+1,current:b})})}};d.fn.calendar.parseOptions=function(a){d(a);return d.extend({},d.parser.parseOptions(a,["weekNumberHeader",{firstDay:"number",fit:"boolean",border:"boolean",showWeek:"boolean"}]))};d.fn.calendar.defaults={width:180,height:180,fit:!1,border:!0,showWeek:!1,firstDay:0,weeks:"SMTWTFS".split(""),months:"Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec".split(" "),year:(new Date).getFullYear(),month:(new Date).getMonth()+1,current:function(){var a=new Date;return new Date(a.getFullYear(),a.getMonth(),a.getDate())}(),weekNumberHeader:"",getWeekNumber:function(a){a=new Date(a.getTime());a.setDate(a.getDate()+4-(a.getDay()||7));var b=a.getTime();a.setMonth(0);a.setDate(1);return Math.floor(Math.round((b-a)/864E5)/7)+1},formatter:function(a){return a.getDate()},styler:function(a){return""},validator:function(a){return!0},onSelect:function(a){},onChange:function(a,b){},onNavigate:function(a,b){}}})(jQuery);