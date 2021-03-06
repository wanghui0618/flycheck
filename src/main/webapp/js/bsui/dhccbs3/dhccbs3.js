/*! Z.L. DHCC_BS3 0.8.8 @jq1.11.1,bs3.3.1 */
if (function(a, b) {
    "object" == typeof module && "object" == typeof module.exports ? module.exports = a.document ? b(a, !0) : function(a) {
        if (!a.document) throw new Error("jQuery requires a window with a document");
        return b(a)
    }: b(a)
} ("undefined" != typeof window ? window: this,
function(a, b) {
    function c(a) {
        var b = a.length,
        c = Bb.type(a);
        return "function" === c || Bb.isWindow(a) ? !1 : 1 === a.nodeType && b ? !0 : "array" === c || 0 === b || "number" == typeof b && b > 0 && b - 1 in a
    }
    function d(a, b, c) {
        if (Bb.isFunction(b)) return Bb.grep(a,
        function(a, d) {
            return !! b.call(a, d, a) !== c
        });
        if (b.nodeType) return Bb.grep(a,
        function(a) {
            return a === b !== c
        });
        if ("string" == typeof b) {
            if (Jb.test(b)) return Bb.filter(b, a, c);
            b = Bb.filter(b, a)
        }
        return Bb.grep(a,
        function(a) {
            return Bb.inArray(a, b) >= 0 !== c
        })
    }
    function e(a, b) {
        do a = a[b];
        while (a && 1 !== a.nodeType);
        return a
    }
    function f(a) {
        var b = Rb[a] = {};
        return Bb.each(a.match(Qb) || [],
        function(a, c) {
            b[c] = !0
        }),
        b
    }
    function g() {
        Lb.addEventListener ? (Lb.removeEventListener("DOMContentLoaded", h, !1), a.removeEventListener("load", h, !1)) : (Lb.detachEvent("onreadystatechange", h), a.detachEvent("onload", h))
    }
    function h() { (Lb.addEventListener || "load" === event.type || "complete" === Lb.readyState) && (g(), Bb.ready())
    }
    function i(a, b, c) {
        if (void 0 === c && 1 === a.nodeType) {
            var d = "data-" + b.replace(Wb, "-$1").toLowerCase();
            if (c = a.getAttribute(d), "string" == typeof c) {
                try {
                    c = "true" === c ? !0 : "false" === c ? !1 : "null" === c ? null: +c + "" === c ? +c: Vb.test(c) ? Bb.parseJSON(c) : c
                } catch(e) {}
                if (Bb.data(a, b, c), "data-options" === d && !Bb._data(a, "parsedAttrs")) {
                    var f = Bb.trim(c);
                    if (f) {
                        var g = f.substring(0, 1),
                        h = f.substring(f.length - 1, 1);
                        "{" != g && (f = "{" + f),
                        "}" != h && (f += "}"),
                        Bb.data(a, new Function("return " + f)() || {})
                    }
                }
            } else c = void 0
        }
        return c
    }
    function j(a) {
        var b;
        for (b in a) if (("data" !== b || !Bb.isEmptyObject(a[b])) && "toJSON" !== b) return ! 1;
        return ! 0
    }
    function k(a, b, c, d) {
        if (Bb.acceptData(a)) {
            var e, f, g = Bb.expando,
            h = a.nodeType,
            i = h ? Bb.cache: a,
            j = h ? a[g] : a[g] && g;
            if (j && i[j] && (d || i[j].data) || void 0 !== c || "string" != typeof b) return j || (j = h ? a[g] = rb.pop() || Bb.guid++:g),
            i[j] || (i[j] = h ? {}: {
                toJSON: Bb.noop
            }),
            ("object" == typeof b || "function" == typeof b) && (d ? i[j] = Bb.extend(i[j], b) : i[j].data = Bb.extend(i[j].data, b)),
            f = i[j],
            d || (f.data || (f.data = {}), f = f.data),
            void 0 !== c && (f[Bb.camelCase(b)] = c),
            "string" == typeof b ? (e = f[b], null == e && (e = f[Bb.camelCase(b)])) : e = f,
            e
        }
    }
    function l(a, b, c) {
        if (Bb.acceptData(a)) {
            var d, e, f = a.nodeType,
            g = f ? Bb.cache: a,
            h = f ? a[Bb.expando] : Bb.expando;
            if (g[h]) {
                if (b && (d = c ? g[h] : g[h].data)) {
                    Bb.isArray(b) ? b = b.concat(Bb.map(b, Bb.camelCase)) : b in d ? b = [b] : (b = Bb.camelCase(b), b = b in d ? [b] : b.split(" ")),
                    e = b.length;
                    for (; e--;) delete d[b[e]];
                    if (c ? !j(d) : !Bb.isEmptyObject(d)) return
                } (c || (delete g[h].data, j(g[h]))) && (f ? Bb.cleanData([a], !0) : zb.deleteExpando || g != g.window ? delete g[h] : g[h] = null)
            }
        }
    }
    function m() {
        return ! 0
    }
    function n() {
        return ! 1
    }
    function o() {
        try {
            return Lb.activeElement
        } catch(a) {}
    }
    function p(a) {
        var b = fc.split("|"),
        c = a.createDocumentFragment();
        if (c.createElement) for (; b.length;) c.createElement(b.pop());
        return c
    }
    function q(a, b) {
        var c, d, e = 0,
        f = typeof a.getElementsByTagName !== Ub ? a.getElementsByTagName(b || "*") : typeof a.querySelectorAll !== Ub ? a.querySelectorAll(b || "*") : void 0;
        if (!f) for (f = [], c = a.childNodes || a; null != (d = c[e]); e++) ! b || Bb.nodeName(d, b) ? f.push(d) : Bb.merge(f, q(d, b));
        return void 0 === b || b && Bb.nodeName(a, b) ? Bb.merge([a], f) : f
    }
    function r(a) {
        _b.test(a.type) && (a.defaultChecked = a.checked)
    }
    function s(a, b) {
        return Bb.nodeName(a, "table") && Bb.nodeName(11 !== b.nodeType ? b: b.firstChild, "tr") ? a.getElementsByTagName("tbody")[0] || a.appendChild(a.ownerDocument.createElement("tbody")) : a
    }
    function t(a) {
        return a.type = (null !== Bb.find.attr(a, "type")) + "/" + a.type,
        a
    }
    function u(a) {
        var b = qc.exec(a.type);
        return b ? a.type = b[1] : a.removeAttribute("type"),
        a
    }
    function v(a, b) {
        for (var c, d = 0; null != (c = a[d]); d++) Bb._data(c, "globalEval", !b || Bb._data(b[d], "globalEval"))
    }
    function w(a, b) {
        if (1 === b.nodeType && Bb.hasData(a)) {
            var c, d, e, f = Bb._data(a),
            g = Bb._data(b, f),
            h = f.events;
            if (h) {
                delete g.handle,
                g.events = {};
                for (c in h) for (d = 0, e = h[c].length; e > d; d++) Bb.event.add(b, c, h[c][d])
            }
            g.data && (g.data = Bb.extend({},
            g.data))
        }
    }
    function x(a, b) {
        var c, d, e;
        if (1 === b.nodeType) {
            if (c = b.nodeName.toLowerCase(), !zb.noCloneEvent && b[Bb.expando]) {
                e = Bb._data(b);
                for (d in e.events) Bb.removeEvent(b, d, e.handle);
                b.removeAttribute(Bb.expando)
            }
            "script" === c && b.text !== a.text ? (t(b).text = a.text, u(b)) : "object" === c ? (b.parentNode && (b.outerHTML = a.outerHTML), zb.html5Clone && a.innerHTML && !Bb.trim(b.innerHTML) && (b.innerHTML = a.innerHTML)) : "input" === c && _b.test(a.type) ? (b.defaultChecked = b.checked = a.checked, b.value !== a.value && (b.value = a.value)) : "option" === c ? b.defaultSelected = b.selected = a.defaultSelected: ("input" === c || "textarea" === c) && (b.defaultValue = a.defaultValue)
        }
    }
    function y(b, c) {
        var d, e = Bb(c.createElement(b)).appendTo(c.body),
        f = a.getDefaultComputedStyle && (d = a.getDefaultComputedStyle(e[0])) ? d.display: Bb.css(e[0], "display");
        return e.detach(),
        f
    }
    function z(a) {
        var b = Lb,
        c = wc[a];
        return c || (c = y(a, b), "none" !== c && c || (vc = (vc || Bb("<iframe frameborder='0' width='0' height='0'/>")).appendTo(b.documentElement), b = (vc[0].contentWindow || vc[0].contentDocument).document, b.write(), b.close(), c = y(a, b), vc.detach()), wc[a] = c),
        c
    }
    function A(a, b) {
        return {
            get: function() {
                var c = a();
                return null != c ? c ? void delete this.get: (this.get = b).apply(this, arguments) : void 0
            }
        }
    }
    function B(a, b) {
        if (b in a) return b;
        for (var c = b.charAt(0).toUpperCase() + b.slice(1), d = b, e = Jc.length; e--;) if (b = Jc[e] + c, b in a) return b;
        return d
    }
    function C(a, b) {
        for (var c, d, e, f = [], g = 0, h = a.length; h > g; g++) d = a[g],
        d.style && (f[g] = Bb._data(d, "olddisplay"), c = d.style.display, b ? (f[g] || "none" !== c || (d.style.display = ""), "" === d.style.display && Zb(d) && (f[g] = Bb._data(d, "olddisplay", z(d.nodeName)))) : (e = Zb(d), (c && "none" !== c || !e) && Bb._data(d, "olddisplay", e ? c: Bb.css(d, "display"))));
        for (g = 0; h > g; g++) d = a[g],
        d.style && (b && "none" !== d.style.display && "" !== d.style.display || (d.style.display = b ? f[g] || "": "none"));
        return a
    }
    function D(a, b, c) {
        var d = Fc.exec(b);
        return d ? Math.max(0, d[1] - (c || 0)) + (d[2] || "px") : b
    }
    function E(a, b, c, d, e) {
        for (var f = c === (d ? "border": "content") ? 4 : "width" === b ? 1 : 0, g = 0; 4 > f; f += 2)"margin" === c && (g += Bb.css(a, c + Yb[f], !0, e)),
        d ? ("content" === c && (g -= Bb.css(a, "padding" + Yb[f], !0, e)), "margin" !== c && (g -= Bb.css(a, "border" + Yb[f] + "Width", !0, e))) : (g += Bb.css(a, "padding" + Yb[f], !0, e), "padding" !== c && (g += Bb.css(a, "border" + Yb[f] + "Width", !0, e)));
        return g
    }
    function F(a, b, c) {
        var d = !0,
        e = "width" === b ? a.offsetWidth: a.offsetHeight,
        f = xc(a),
        g = zb.boxSizing && "border-box" === Bb.css(a, "boxSizing", !1, f);
        if (0 >= e || null == e) {
            if (e = yc(a, b, f), (0 > e || null == e) && (e = a.style[b]), Ac.test(e)) return e;
            d = g && (zb.boxSizingReliable() || e === a.style[b]),
            e = parseFloat(e) || 0
        }
        return e + E(a, b, c || (g ? "border": "content"), d, f) + "px"
    }
    function G(a, b, c, d, e) {
        return new G.prototype.init(a, b, c, d, e)
    }
    function H() {
        return setTimeout(function() {
            Kc = void 0
        }),
        Kc = Bb.now()
    }
    function I(a, b) {
        var c, d = {
            height: a
        },
        e = 0;
        for (b = b ? 1 : 0; 4 > e; e += 2 - b) c = Yb[e],
        d["margin" + c] = d["padding" + c] = a;
        return b && (d.opacity = d.width = a),
        d
    }
    function J(a, b, c) {
        for (var d, e = (Qc[b] || []).concat(Qc["*"]), f = 0, g = e.length; g > f; f++) if (d = e[f].call(c, b, a)) return d
    }
    function K(a, b, c) {
        var d, e, f, g, h, i, j, k, l = this,
        m = {},
        n = a.style,
        o = a.nodeType && Zb(a),
        p = Bb._data(a, "fxshow");
        c.queue || (h = Bb._queueHooks(a, "fx"), null == h.unqueued && (h.unqueued = 0, i = h.empty.fire, h.empty.fire = function() {
            h.unqueued || i()
        }), h.unqueued++, l.always(function() {
            l.always(function() {
                h.unqueued--,
                Bb.queue(a, "fx").length || h.empty.fire()
            })
        })),
        1 === a.nodeType && ("height" in b || "width" in b) && (c.overflow = [n.overflow, n.overflowX, n.overflowY], j = Bb.css(a, "display"), k = "none" === j ? Bb._data(a, "olddisplay") || z(a.nodeName) : j, "inline" === k && "none" === Bb.css(a, "float") && (zb.inlineBlockNeedsLayout && "inline" !== z(a.nodeName) ? n.zoom = 1 : n.display = "inline-block")),
        c.overflow && (n.overflow = "hidden", zb.shrinkWrapBlocks() || l.always(function() {
            n.overflow = c.overflow[0],
            n.overflowX = c.overflow[1],
            n.overflowY = c.overflow[2]
        }));
        for (d in b) if (e = b[d], Mc.exec(e)) {
            if (delete b[d], f = f || "toggle" === e, e === (o ? "hide": "show")) {
                if ("show" !== e || !p || void 0 === p[d]) continue;
                o = !0
            }
            m[d] = p && p[d] || Bb.style(a, d)
        } else j = void 0;
        if (Bb.isEmptyObject(m))"inline" === ("none" === j ? z(a.nodeName) : j) && (n.display = j);
        else {
            p ? "hidden" in p && (o = p.hidden) : p = Bb._data(a, "fxshow", {}),
            f && (p.hidden = !o),
            o ? Bb(a).show() : l.done(function() {
                Bb(a).hide()
            }),
            l.done(function() {
                var b;
                Bb._removeData(a, "fxshow");
                for (b in m) Bb.style(a, b, m[b])
            });
            for (d in m) g = J(o ? p[d] : 0, d, l),
            d in p || (p[d] = g.start, o && (g.end = g.start, g.start = "width" === d || "height" === d ? 1 : 0))
        }
    }
    function L(a, b) {
        var c, d, e, f, g;
        for (c in a) if (d = Bb.camelCase(c), e = b[d], f = a[c], Bb.isArray(f) && (e = f[1], f = a[c] = f[0]), c !== d && (a[d] = f, delete a[c]), g = Bb.cssHooks[d], g && "expand" in g) {
            f = g.expand(f),
            delete a[d];
            for (c in f) c in a || (a[c] = f[c], b[c] = e)
        } else b[d] = e
    }
    function M(a, b, c) {
        var d, e, f = 0,
        g = Pc.length,
        h = Bb.Deferred().always(function() {
            delete i.elem
        }),
        i = function() {
            if (e) return ! 1;
            for (var b = Kc || H(), c = Math.max(0, j.startTime + j.duration - b), d = c / j.duration || 0, f = 1 - d, g = 0, i = j.tweens.length; i > g; g++) j.tweens[g].run(f);
            return h.notifyWith(a, [j, f, c]),
            1 > f && i ? c: (h.resolveWith(a, [j]), !1)
        },
        j = h.promise({
            elem: a,
            props: Bb.extend({},
            b),
            opts: Bb.extend(!0, {
                specialEasing: {}
            },
            c),
            originalProperties: b,
            originalOptions: c,
            startTime: Kc || H(),
            duration: c.duration,
            tweens: [],
            createTween: function(b, c) {
                var d = Bb.Tween(a, j.opts, b, c, j.opts.specialEasing[b] || j.opts.easing);
                return j.tweens.push(d),
                d
            },
            stop: function(b) {
                var c = 0,
                d = b ? j.tweens.length: 0;
                if (e) return this;
                for (e = !0; d > c; c++) j.tweens[c].run(1);
                return b ? h.resolveWith(a, [j, b]) : h.rejectWith(a, [j, b]),
                this
            }
        }),
        k = j.props;
        for (L(k, j.opts.specialEasing); g > f; f++) if (d = Pc[f].call(j, a, k, j.opts)) return d;
        return Bb.map(k, J, j),
        Bb.isFunction(j.opts.start) && j.opts.start.call(a, j),
        Bb.fx.timer(Bb.extend(i, {
            elem: a,
            anim: j,
            queue: j.opts.queue
        })),
        j.progress(j.opts.progress).done(j.opts.done, j.opts.complete).fail(j.opts.fail).always(j.opts.always)
    }
    function N(a) {
        return function(b, c) {
            "string" != typeof b && (c = b, b = "*");
            var d, e = 0,
            f = b.toLowerCase().match(Qb) || [];
            if (Bb.isFunction(c)) for (; d = f[e++];)"+" === d.charAt(0) ? (d = d.slice(1) || "*", (a[d] = a[d] || []).unshift(c)) : (a[d] = a[d] || []).push(c)
        }
    }
    function O(a, b, c, d) {
        function e(h) {
            var i;
            return f[h] = !0,
            Bb.each(a[h] || [],
            function(a, h) {
                var j = h(b, c, d);
                return "string" != typeof j || g || f[j] ? g ? !(i = j) : void 0 : (b.dataTypes.unshift(j), e(j), !1)
            }),
            i
        }
        var f = {},
        g = a === md;
        return e(b.dataTypes[0]) || !f["*"] && e("*")
    }
    function P(a, b) {
        var c, d, e = Bb.ajaxSettings.flatOptions || {};
        for (d in b) void 0 !== b[d] && ((e[d] ? a: c || (c = {}))[d] = b[d]);
        return c && Bb.extend(!0, a, c),
        a
    }
    function Q(a, b, c) {
        for (var d, e, f, g, h = a.contents,
        i = a.dataTypes;
        "*" === i[0];) i.shift(),
        void 0 === e && (e = a.mimeType || b.getResponseHeader("Content-Type"));
        if (e) for (g in h) if (h[g] && h[g].test(e)) {
            i.unshift(g);
            break
        }
        if (i[0] in c) f = i[0];
        else {
            for (g in c) {
                if (!i[0] || a.converters[g + " " + i[0]]) {
                    f = g;
                    break
                }
                d || (d = g)
            }
            f = f || d
        }
        return f ? (f !== i[0] && i.unshift(f), c[f]) : void 0
    }
    function R(a, b, c, d) {
        var e, f, g, h, i, j = {},
        k = a.dataTypes.slice();
        if (k[1]) for (g in a.converters) j[g.toLowerCase()] = a.converters[g];
        for (f = k.shift(); f;) if (a.responseFields[f] && (c[a.responseFields[f]] = b), !i && d && a.dataFilter && (b = a.dataFilter(b, a.dataType)), i = f, f = k.shift()) if ("*" === f) f = i;
        else if ("*" !== i && i !== f) {
            if (g = j[i + " " + f] || j["* " + f], !g) for (e in j) if (h = e.split(" "), h[1] === f && (g = j[i + " " + h[0]] || j["* " + h[0]])) {
                g === !0 ? g = j[e] : j[e] !== !0 && (f = h[0], k.unshift(h[1]));
                break
            }
            if (g !== !0) if (g && a["throws"]) b = g(b);
            else try {
                b = g(b)
            } catch(l) {
                return {
                    state: "parsererror",
                    error: g ? l: "No conversion from " + i + " to " + f
                }
            }
        }
        return {
            state: "success",
            data: b
        }
    }
    function S(a, b, c, d) {
        var e;
        if (Bb.isArray(b)) Bb.each(b,
        function(b, e) {
            c || qd.test(a) ? d(a, e) : S(a + "[" + ("object" == typeof e ? b: "") + "]", e, c, d)
        });
        else if (c || "object" !== Bb.type(b)) d(a, b);
        else for (e in b) S(a + "[" + e + "]", b[e], c, d)
    }
    function T() {
        try {
            return new a.XMLHttpRequest
        } catch(b) {}
    }
    function U() {
        try {
            return new a.ActiveXObject("Microsoft.XMLHTTP")
        } catch(b) {}
    }
    function V(a) {
        return Bb.isWindow(a) ? a: 9 === a.nodeType ? a.defaultView || a.parentWindow: !1
    }
    function W() {
        var b = a.location,
        c = b.host,
        d = b.protocol,
        e = b.pathname,
        f = e.split("/"),
        g = d + "//" + c + ("/" === e ? "": "/" + f[1]);
        return g
    }
    function X(a) {
        var b = {
            name: "",
            css: [],
            js: [],
            callback: Bb.noop,
            deps: []
        };
        if ("string" === Bb.type(a)) {
            if (a = Bb.trim(a), Hd[a]) return Bb.extend(b, Hd[a]),
            b;
            if (Gd[a] && Hd[Gd[a]]) return Bb.extend(b, Hd[Gd[a]]),
            b;
            if (/.css$/i.test(a) ? b.css.push(a) : /.js$/i.test(a) && b.js.push(a), 0 == b.js.length + b.css.length) return ! 1;
            var c = b.name = "_M__" + ++Bd;
            return Gd[a] = c,
            Hd[c] = b,
            b
        }
        if (Bb.isPlainObject(a)) {
            var c = "" + b.name || "";
            return Hd[c] ? (Bb.extend(b, Hd[c]), b) : (Bb.extend(b, a), b.name = c = "_M__" + ++Bd, Hd[c] = b, b)
        }
        return ! 1
    }
    function Y(a, b) {
        for (var c = 0; c < b.length; c++) if (a.name === b[c].name) return c;
        return - 1
    }
    function Z(a) {
        Bb.isArray(a) || (a = [a]);
        for (var b = [], c = 0; c < a.length; c++) {
            var d = X(a[c]),
            e = []; ! 1 !== d && (d.deps.length && (e = Z(d.deps.slice())), e.push(d), Bb.each(e,
            function(a, c) { - 1 === Y(c, b) && b.push(c)
            }))
        }
        return b
    }
    function _() {
        return (new Date).getTime()
    }
    function ab(a) {
        return ! Fd[a] || "loaded" !== Fd[a] && "overtime" !== Fd[a] && "404" !== Fd[a] ? !1 : !0
    }
    function bb(a) {
        return Fd[a] && "loaded" === Fd[a] ? !0 : !1
    }
    function cb(a, b) {
        Fd[a] = b
    }
    function db(a) {
        return Fd[a] || void 0
    }
    function eb() {}
    function fb(a) {
        Bb.error(a)
    }
    function gb() {
        function a(c) {
            var d = Bb.Deferred();
            return Kd.length ? (f = Kd.shift(), b().done(function() {
                Kd.length && Kd[0].state && (c.resolve(), c = Kd.shift()),
                a(c)
            }).fail(function(b) {
                if (eb("debug", "[" + f.name + " : ", this.src, "] Exception:", b), fb(["Loading", "[" + f.name + " : ", this.src, "] Exception:", b].join(" ")), c.rejectWith(this, arguments), Kd.length) {
                    c = null;
                    for (var d = Kd.shift(); ! d.state && Kd.length;) d = Kd.shift();
                    d.state && (c = Kd.shift(), a(c))
                }
            })) : c.state && (c.resolve(), Jd = !1),
            d.promise()
        }
        function b() {
            var a, b = Bb.Deferred();
            return bb(f.name) ? (b.resolve(), b.promise()) : (g = Array.prototype.concat(f.css, f.js), g.length ? (cb(f.name, "loading"), a = c().done(function() {
                f.callback && f.callback.apply(Id, [f]),
                b.resolve()
            }).fail(function() {
                b.rejectWith(this, arguments)
            })) : b.resolve(), b.promise())
        }
        function c(a) {
            var b = Bb.Deferred(),
            e = a ? a: b;
            return g.length ? (h = g.shift(), d().done(function() {
                c(e)
            }).fail(function() {
                e.rejectWith(this, arguments)
            })) : e && e.resolve(),
            b.promise()
        }
        function d() {
            var a = Bb.Deferred(),
            b = _(),
            c = "",
            d = {
                src: "",
                charset: "UTF-8",
                callback: Bb.noop
            };
            if (Bb.isPlainObject(h) ? Bb.extend(d, h) : d.src = hb(h), !d.src) return a.resolve(),
            a.promise();
            if (c = /\.js$/i.test(d.src) ? "js": "css", bb(d.src)) return a.resolve(),
            a.promise();
            cb(d.src, "loading"),
            eb("debug", b, db(d.src), d.src);
            var f;
            if ("js" == c) {
                var g = !1;
                f = Lb.createElement("script"),
                f.type = "text/javascript",
                f.language = "javascript",
                f.charset = d.charset,
                f.src = d.src,
                f.onload = f.onreadystatechange = function() {
                    g || f.readyState && "loaded" != f.readyState && "complete" != f.readyState || (g = !0, f.onload = f.onerror = f.onreadystatechange = null, setTimeout(function() {
                        cb(d.src, "loaded")
                    },
                    Math.min(100, (_() - b) / 2)))
                }
            } else f = Lb.createElement("link"),
            f.rel = "stylesheet",
            f.type = "text/css",
            f.charset = d.charset,
            f.href = d.src,
            f.attachEvent && f.attachEvent("onload",
            function() {
                f.onload = f.onerror = f.onreadystatechange = null,
                setTimeout(function() {
                    cb(d.src, "loaded")
                },
                1)
            });
            var i, j = 2e3,
            k = !1;
            return i = setInterval(function() {
                return j -= 5,
                0 > j ? (clearInterval(i), i = null, cb(d.src, "overtime"), a.rejectWith(d, ["overtime"]), void eb("debug", _(), db(d.src), d.src)) : void(k || (k = !0, ab(d.src) || "css" != c || f.attachEvent || e(f) && cb(d.src, "loaded"), ab(d.src) && (eb("debug", _(), db(d.src), d.src), clearInterval(i), i = null, bb(d.src) ? (d.callback && d.callback.apply(Id, [d, f]), setTimeout(function() {
                    a.resolve()
                },
                1)) : a.rejectWith(d, [db(d.src)])), k = !1))
            },
            5),
            f.onerror = function() {
                f.onload = f.onerror = f.onreadystatechange = null,
                this.parentNode.removeChild(this),
                cb(d.src, "404")
            },
            Nd ? Md.insertBefore(f, baseElement) : Md.appendChild(f),
            a.promise()
        }
        function e(a) {
            var b = !1;
            if (/webkit/i.test(navigator.userAgent)) a.sheet && (b = !0);
            else if (a.sheet) try {
                a.sheet.cssRules && (b = !0)
            } catch(c) {
                1e3 === c.code && (b = !0)
            }
            return b
        }
        if (!Jd) {
            var f, g, h;
            Jd = !0,
            Kd.length && a(Kd.shift())
        }
    }
    function hb(a) {
        var b = Bb.trim(a).match(/([^?]+\.(js|css))(\?[^/\\]+)?$/i);
        return b && b[1] ? "" + b[1] : "";
    }
    function ib(a) {
        var b = Z(a);
        Bb.each(b,
        function(a, b) {
            if (b) {
                Bb.each(b.css.concat(b.js),
                function(a, b) {
                    var c = hb(b);
                    c && cb(c, "loaded")
                });
                var c = Bb.trim(b.name);
                c && cb(c, "loaded")
            }
        })
    }
    function jb() {
        Cd || (Ed && ib(["bootstrap"]), Cd = !0)
    }
    function kb(a) {
        var b, c = {
            skipNames: [],
            skipHidden: !1,
            skipDisabled: !1
        },
        d = {};
        if (Bb.extend(c, a), Bb.isArray(c.skipNames) || (c.skipNames = [c.skipNames]), b = $(this).find("[name]").filter(":text, input:hidden, :password, :checkbox, :radio, select, textarea"), c.skipHidden && (b = b.not(":hidden")), c.skipDisabled && (b = b.not(":disabled")), c.skipNames) {
            var e = [];
            Bb.each(c.skipNames,
            function() {
                var a = this.replace(/\[\]/g, "");
                e.push('[name="' + a + '"]'),
                e.push('[name="' + a + '[]"]')
            }),
            e.length && (b = b.not(e.join(", ")))
        }
        var f = b.filter(":checkbox, :radio");
        return Bb.each(f,
        function() {
            var a = $(this),
            b = a.attr("name").replace(/\[\]/g, ""),
            c = a.val(); (void 0 === c || null === c) && (c = ""),
            void 0 === d[b] && (d[b] = ""),
            a.prop("checked") && ("" === d[b] ? d[b] = c: (d[b] = [d[b]], d[b].push(c)))
        }),
        b = b.not(":checkbox, :radio"),
        Bb.each(b,
        function() {
            var a = $(this),
            b = a.attr("name").replace(/\[\]/g, ""),
            c = a.val(); (void 0 === c || null === c) && (c = ""),
            Bb.extend(d, Bb.parseJSON('{"' + b + '":' + Bb.toJSON(c) + "}"))
        }),
        d
    }
    function lb(a, b) {
        var c = $(this),
        d = null,
        e = {
            skipNames: [],
            skipHidden: !1,
            skipDisabled: !1
        };
        if (Bb.extend(e, b), Bb.isArray(e.skipNames) || (e.skipNames = [e.skipNames]), void 0 !== a && String === a.constructor && (a = Bb.parseJSON(a)), a) {
            var f = mb(a);
            e.skipNames && Bb.each(e.skipNames,
            function() {
                var a = this.replace(/\[\]/g, "");
                try {
                    delete f[a]
                } catch(b) {}
                try {
                    delete f[a + "[]"]
                } catch(b) {}
            }),
            Bb.each(f,
            function(a, b) {
                a = a.replace(/\[\]/g, "");
                var f = c.find('[name="' + a + '"], [name="' + a + '[]"]');
                if (e.skipHidden && (f = f.not(":hidden")), e.skipDisabled && (f = f.not(":disabled")), f.length) {
                    d = d ? d.add(f) : f;
                    var g = f.filter(":checkbox, :radio");
                    if (g.length) {
                        var h = void 0 === b || null === b ? [] : Bb.isArray(b) ? Bb.map(b,
                        function(a) {
                            return "" + a
                        }) : ["" + b];
                        f.prop("checked", !1),
                        Bb.each(g,
                        function() {
                            var a = $(this);
                            Bb.inArray(a.val(), h) > -1 && a.prop("checked", !0)
                        })
                    }
                    f = f.not(":checkbox, :radio"),
                    Bb.each(f,
                    function() {
                        $(this).val(b),
                        $(this).chosen && $(this).trigger("chosen:updated")
                    })
                }
            })
        }
        return d
    }
    function mb(a, b) { (void 0 === b || null === b || "" === b) && (b = "");
        var c, d, e = {},
        f = {};
        for (c in a) d = b ? [b, c].join(".") : c,
        Bb.isPlainObject(a[c]) ? (f = mb(a[c], d), Bb.extend(e, f)) : e[d] = a[c];
        return e
    }
    function nb(a) {
        function b(a, c, d) {
            if (c.length) {
                var e = c.shift();
                c.length ? (void 0 === a[e] && (a[e] = {}), b(a[e], c, d)) : a[e] = d
            }
        }
        var c = {};
        return Bb.each(a,
        function(a) {
            var d = a.split(".");
            b(c, d, this)
        }),
        c
    }
    function ob(a) {
        var b = [];
        return Bb.each(a,
        function(a, c) {
            $.isArray(c) ? Bb.each(c,
            function(c, d) {
                b.push({
                    name: a,
                    value: d
                })
            }) : b.push({
                name: a,
                value: c
            })
        }),
        b
    }
    function pb(a, b, c) {
        var d = this;
        alert(403 == a.status && "UNAUTHORIZED" === a.statusText ? ["没有该操作相应权限，请检查后再试"].join(" ") : 504 == a.status && "overDue" === a.statusText ? ["登录过期，请重新登录"].join(" ") : [d.type, d.url, a.status, c].join(" "))
    }
    function qb(a) {
        var b = /["\\\x00-\x1f\x7f-\x9f]/g,
        c = {
            "\b": "\\b",
            "	": "\\t",
            "\n": "\\n",
            "\f": "\\f",
            "\r": "\\r",
            '"': '\\"',
            "\\": "\\\\"
        };
        return a.match(b) ? '"' + a.replace(b,
        function(a) {
            var b = c[a];
            return "string" == typeof b ? b: (b = a.charCodeAt(), "\\u00" + Math.floor(b / 16).toString(16) + (b % 16).toString(16))
        }) + '"': '"' + a + '"'
    }
    var rb = [], sb = rb.slice, tb = rb.concat, ub = rb.push, vb = rb.indexOf, wb = {},
    xb = wb.toString, yb = wb.hasOwnProperty, zb = {},
    Ab = "1.11.1", Bb = function(a, b) {
        return new Bb.fn.init(a, b)
    },
    Cb = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, Db = /^-ms-/, Eb = /-([\da-z])/gi, Fb = function(a, b) {
        return b.toUpperCase()
    }; Bb.fn = Bb.prototype = {
        jquery: Ab,
        constructor: Bb,
        selector: "",
        length: 0,
        toArray: function() {
            return sb.call(this)
        },
        get: function(a) {
            return null != a ? 0 > a ? this[a + this.length] : this[a] : sb.call(this)
        },
        pushStack: function(a) {
            var b = Bb.merge(this.constructor(), a);
            return b.prevObject = this,
            b.context = this.context,
            b
        },
        each: function(a, b) {
            return Bb.each(this, a, b)
        },
        map: function(a) {
            return this.pushStack(Bb.map(this,
            function(b, c) {
                return a.call(b, c, b)
            }))
        },
        slice: function() {
            return this.pushStack(sb.apply(this, arguments))
        },
        first: function() {
            return this.eq(0)
        },
        last: function() {
            return this.eq( - 1)
        },
        eq: function(a) {
            var b = this.length,
            c = +a + (0 > a ? b: 0);
            return this.pushStack(c >= 0 && b > c ? [this[c]] : [])
        },
        end: function() {
            return this.prevObject || this.constructor(null)
        },
        push: ub,
        sort: rb.sort,
        splice: rb.splice
    },
    Bb.extend = Bb.fn.extend = function() {
        var a, b, c, d, e, f, g = arguments[0] || {},
        h = 1,
        i = arguments.length,
        j = !1;
        for ("boolean" == typeof g && (j = g, g = arguments[h] || {},
        h++), "object" == typeof g || Bb.isFunction(g) || (g = {}), h === i && (g = this, h--); i > h; h++) if (null != (e = arguments[h])) for (d in e) a = g[d],
        c = e[d],
        g !== c && (j && c && (Bb.isPlainObject(c) || (b = Bb.isArray(c))) ? (b ? (b = !1, f = a && Bb.isArray(a) ? a: []) : f = a && Bb.isPlainObject(a) ? a: {},
        g[d] = Bb.extend(j, f, c)) : void 0 !== c && (g[d] = c));
        return g
    },
    Bb.extend({
        expando: "jQuery" + (Ab + Math.random()).replace(/\D/g, ""),
        isReady: !0,
        error: function(a) {
            throw new Error(a)
        },
        noop: function() {},
        isFunction: function(a) {
            return "function" === Bb.type(a)
        },
        isArray: Array.isArray ||
        function(a) {
            return "array" === Bb.type(a)
        },
        isWindow: function(a) {
            return null != a && a == a.window
        },
        isNumeric: function(a) {
            return ! Bb.isArray(a) && a - parseFloat(a) >= 0
        },
        isEmptyObject: function(a) {
            var b;
            for (b in a) return ! 1;
            return ! 0
        },
        isPlainObject: function(a) {
            var b;
            if (!a || "object" !== Bb.type(a) || a.nodeType || Bb.isWindow(a)) return ! 1;
            try {
                if (a.constructor && !yb.call(a, "constructor") && !yb.call(a.constructor.prototype, "isPrototypeOf")) return ! 1
            } catch(c) {
                return ! 1
            }
            if (zb.ownLast) for (b in a) return yb.call(a, b);
            for (b in a);
            return void 0 === b || yb.call(a, b)
        },
        type: function(a) {
            return null == a ? a + "": "object" == typeof a || "function" == typeof a ? wb[xb.call(a)] || "object": typeof a
        },
        globalEval: function(b) {
            b && Bb.trim(b) && (a.execScript ||
            function(b) {
                a.eval.call(a, b)
            })(b)
        },
        camelCase: function(a) {
            return a.replace(Db, "ms-").replace(Eb, Fb)
        },
        nodeName: function(a, b) {
            return a.nodeName && a.nodeName.toLowerCase() === b.toLowerCase()
        },
        each: function(a, b, d) {
            var e, f = 0,
            g = a.length,
            h = c(a);
            if (d) {
                if (h) for (; g > f && (e = b.apply(a[f], d), e !== !1); f++);
                else for (f in a) if (e = b.apply(a[f], d), e === !1) break
            } else if (h) for (; g > f && (e = b.call(a[f], f, a[f]), e !== !1); f++);
            else for (f in a) if (e = b.call(a[f], f, a[f]), e === !1) break;
            return a
        },
        trim: function(a) {
            return null == a ? "": (a + "").replace(Cb, "")
        },
        makeArray: function(a, b) {
            var d = b || [];
            return null != a && (c(Object(a)) ? Bb.merge(d, "string" == typeof a ? [a] : a) : ub.call(d, a)),
            d
        },
        inArray: function(a, b, c) {
            var d;
            if (b) {
                if (vb) return vb.call(b, a, c);
                for (d = b.length, c = c ? 0 > c ? Math.max(0, d + c) : c: 0; d > c; c++) if (c in b && b[c] === a) return c
            }
            return - 1
        },
        merge: function(a, b) {
            for (var c = +b.length,
            d = 0,
            e = a.length; c > d;) a[e++] = b[d++];
            if (c !== c) for (; void 0 !== b[d];) a[e++] = b[d++];
            return a.length = e,
            a
        },
        grep: function(a, b, c) {
            for (var d, e = [], f = 0, g = a.length, h = !c; g > f; f++) d = !b(a[f], f),
            d !== h && e.push(a[f]);
            return e
        },
        map: function(a, b, d) {
            var e, f = 0,
            g = a.length,
            h = c(a),
            i = [];
            if (h) for (; g > f; f++) e = b(a[f], f, d),
            null != e && i.push(e);
            else for (f in a) e = b(a[f], f, d),
            null != e && i.push(e);
            return tb.apply([], i)
        },
        guid: 1,
        proxy: function(a, b) {
            var c, d, e;
            return "string" == typeof b && (e = a[b], b = a, a = e),
            Bb.isFunction(a) ? (c = sb.call(arguments, 2), d = function() {
                return a.apply(b || this, c.concat(sb.call(arguments)))
            },
            d.guid = a.guid = a.guid || Bb.guid++, d) : void 0
        },
        now: function() {
            return + new Date
        },
        support: zb
    }), Bb.each("Boolean Number String Function Array Date RegExp Object Error".split(" "),
    function(a, b) {
        wb["[object " + b + "]"] = b.toLowerCase()
    });
    var Gb = function(a) {
        function b(a, b, c, d) {
            var e, f, g, h, i, j, l, n, o, p;
            if ((b ? b.ownerDocument || b: O) !== G && F(b), b = b || G, c = c || [], !a || "string" != typeof a) return c;
            if (1 !== (h = b.nodeType) && 9 !== h) return [];
            if (I && !d) {
                if (e = sb.exec(a)) if (g = e[1]) {
                    if (9 === h) {
                        if (f = b.getElementById(g), !f || !f.parentNode) return c;
                        if (f.id === g) return c.push(f),
                        c
                    } else if (b.ownerDocument && (f = b.ownerDocument.getElementById(g)) && M(b, f) && f.id === g) return c.push(f),
                    c
                } else {
                    if (e[2]) return _.apply(c, b.getElementsByTagName(a)),
                    c;
                    if ((g = e[3]) && v.getElementsByClassName && b.getElementsByClassName) return _.apply(c, b.getElementsByClassName(g)),
                    c
                }
                if (v.qsa && (!J || !J.test(a))) {
                    if (n = l = N, o = b, p = 9 === h && a, 1 === h && "object" !== b.nodeName.toLowerCase()) {
                        for (j = z(a), (l = b.getAttribute("id")) ? n = l.replace(ub, "\\$&") : b.setAttribute("id", n), n = "[id='" + n + "'] ", i = j.length; i--;) j[i] = n + m(j[i]);
                        o = tb.test(a) && k(b.parentNode) || b,
                        p = j.join(",")
                    }
                    if (p) try {
                        return _.apply(c, o.querySelectorAll(p)),
                        c
                    } catch(q) {} finally {
                        l || b.removeAttribute("id")
                    }
                }
            }
            return B(a.replace(ib, "$1"), b, c, d)
        }
        function c() {
            function a(c, d) {
                return b.push(c + " ") > w.cacheLength && delete a[b.shift()],
                a[c + " "] = d
            }
            var b = [];
            return a
        }
        function d(a) {
            return a[N] = !0,
            a
        }
        function e(a) {
            var b = G.createElement("div");
            try {
                return !! a(b)
            } catch(c) {
                return ! 1
            } finally {
                b.parentNode && b.parentNode.removeChild(b),
                b = null
            }
        }
        function f(a, b) {
            for (var c = a.split("|"), d = a.length; d--;) w.attrHandle[c[d]] = b
        }
        function g(a, b) {
            var c = b && a,
            d = c && 1 === a.nodeType && 1 === b.nodeType && (~b.sourceIndex || W) - (~a.sourceIndex || W);
            if (d) return d;
            if (c) for (; c = c.nextSibling;) if (c === b) return - 1;
            return a ? 1 : -1
        }
        function h(a) {
            return function(b) {
                var c = b.nodeName.toLowerCase();
                return "input" === c && b.type === a
            }
        }
        function i(a) {
            return function(b) {
                var c = b.nodeName.toLowerCase();
                return ("input" === c || "button" === c) && b.type === a
            }
        }
        function j(a) {
            return d(function(b) {
                return b = +b,
                d(function(c, d) {
                    for (var e, f = a([], c.length, b), g = f.length; g--;) c[e = f[g]] && (c[e] = !(d[e] = c[e]))
                })
            })
        }
        function k(a) {
            return a && typeof a.getElementsByTagName !== V && a
        }
        function l() {}
        function m(a) {
            for (var b = 0,
            c = a.length,
            d = ""; c > b; b++) d += a[b].value;
            return d
        }
        function n(a, b, c) {
            var d = b.dir,
            e = c && "parentNode" === d,
            f = Q++;
            return b.first ?
            function(b, c, f) {
                for (; b = b[d];) if (1 === b.nodeType || e) return a(b, c, f)
            }: function(b, c, g) {
                var h, i, j = [P, f];
                if (g) {
                    for (; b = b[d];) if ((1 === b.nodeType || e) && a(b, c, g)) return ! 0
                } else for (; b = b[d];) if (1 === b.nodeType || e) {
                    if (i = b[N] || (b[N] = {}), (h = i[d]) && h[0] === P && h[1] === f) return j[2] = h[2];
                    if (i[d] = j, j[2] = a(b, c, g)) return ! 0
                }
            }
        }
        function o(a) {
            return a.length > 1 ?
            function(b, c, d) {
                for (var e = a.length; e--;) if (!a[e](b, c, d)) return ! 1;
                return ! 0
            }: a[0]
        }
        function p(a, c, d) {
            for (var e = 0,
            f = c.length; f > e; e++) b(a, c[e], d);
            return d
        }
        function q(a, b, c, d, e) {
            for (var f, g = [], h = 0, i = a.length, j = null != b; i > h; h++)(f = a[h]) && (!c || c(f, d, e)) && (g.push(f), j && b.push(h));
            return g
        }
        function r(a, b, c, e, f, g) {
            return e && !e[N] && (e = r(e)),
            f && !f[N] && (f = r(f, g)),
            d(function(d, g, h, i) {
                var j, k, l, m = [],
                n = [],
                o = g.length,
                r = d || p(b || "*", h.nodeType ? [h] : h, []),
                s = !a || !d && b ? r: q(r, m, a, h, i),
                t = c ? f || (d ? a: o || e) ? [] : g: s;
                if (c && c(s, t, h, i), e) for (j = q(t, n), e(j, [], h, i), k = j.length; k--;)(l = j[k]) && (t[n[k]] = !(s[n[k]] = l));
                if (d) {
                    if (f || a) {
                        if (f) {
                            for (j = [], k = t.length; k--;)(l = t[k]) && j.push(s[k] = l);
                            f(null, t = [], j, i)
                        }
                        for (k = t.length; k--;)(l = t[k]) && (j = f ? bb.call(d, l) : m[k]) > -1 && (d[j] = !(g[j] = l))
                    }
                } else t = q(t === g ? t.splice(o, t.length) : t),
                f ? f(null, g, t, i) : _.apply(g, t)
            })
        }
        function s(a) {
            for (var b, c, d, e = a.length,
            f = w.relative[a[0].type], g = f || w.relative[" "], h = f ? 1 : 0, i = n(function(a) {
                return a === b
            },
            g, !0), j = n(function(a) {
                return bb.call(b, a) > -1
            },
            g, !0), k = [function(a, c, d) {
                return ! f && (d || c !== C) || ((b = c).nodeType ? i(a, c, d) : j(a, c, d))
            }]; e > h; h++) if (c = w.relative[a[h].type]) k = [n(o(k), c)];
            else {
                if (c = w.filter[a[h].type].apply(null, a[h].matches), c[N]) {
                    for (d = ++h; e > d && !w.relative[a[d].type]; d++);
                    return r(h > 1 && o(k), h > 1 && m(a.slice(0, h - 1).concat({
                        value: " " === a[h - 2].type ? "*": ""
                    })).replace(ib, "$1"), c, d > h && s(a.slice(h, d)), e > d && s(a = a.slice(d)), e > d && m(a))
                }
                k.push(c)
            }
            return o(k)
        }
        function t(a, c) {
            var e = c.length > 0,
            f = a.length > 0,
            g = function(d, g, h, i, j) {
                var k, l, m, n = 0,
                o = "0",
                p = d && [],
                r = [],
                s = C,
                t = d || f && w.find.TAG("*", j),
                u = P += null == s ? 1 : Math.random() || .1,
                v = t.length;
                for (j && (C = g !== G && g); o !== v && null != (k = t[o]); o++) {
                    if (f && k) {
                        for (l = 0; m = a[l++];) if (m(k, g, h)) {
                            i.push(k);
                            break
                        }
                        j && (P = u)
                    }
                    e && ((k = !m && k) && n--, d && p.push(k))
                }
                if (n += o, e && o !== n) {
                    for (l = 0; m = c[l++];) m(p, r, g, h);
                    if (d) {
                        if (n > 0) for (; o--;) p[o] || r[o] || (r[o] = Z.call(i));
                        r = q(r)
                    }
                    _.apply(i, r),
                    j && !d && r.length > 0 && n + c.length > 1 && b.uniqueSort(i)
                }
                return j && (P = u, C = s),
                p
            };
            return e ? d(g) : g
        }
        var u, v, w, x, y, z, A, B, C, D, E, F, G, H, I, J, K, L, M, N = "sizzle" + -new Date,
        O = a.document,
        P = 0,
        Q = 0,
        R = c(),
        S = c(),
        T = c(),
        U = function(a, b) {
            return a === b && (E = !0),
            0
        },
        V = "undefined",
        W = 1 << 31,
        X = {}.hasOwnProperty,
        Y = [],
        Z = Y.pop,
        $ = Y.push,
        _ = Y.push,
        ab = Y.slice,
        bb = Y.indexOf ||
        function(a) {
            for (var b = 0,
            c = this.length; c > b; b++) if (this[b] === a) return b;
            return - 1
        },
        cb = "checked|selected|async|autofocus|autoplay|controls|defer|disabled|hidden|ismap|loop|multiple|open|readonly|required|scoped",
        db = "[\\x20\\t\\r\\n\\f]",
        eb = "(?:\\\\.|[\\w-]|[^\\x00-\\xa0])+",
        fb = eb.replace("w", "w#"),
        gb = "\\[" + db + "*(" + eb + ")(?:" + db + "*([*^$|!~]?=)" + db + "*(?:'((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\"|(" + fb + "))|)" + db + "*\\]",
        hb = ":(" + eb + ")(?:\\((('((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\")|((?:\\\\.|[^\\\\()[\\]]|" + gb + ")*)|.*)\\)|)",
        ib = new RegExp("^" + db + "+|((?:^|[^\\\\])(?:\\\\.)*)" + db + "+$", "g"),
        jb = new RegExp("^" + db + "*," + db + "*"),
        kb = new RegExp("^" + db + "*([>+~]|" + db + ")" + db + "*"),
        lb = new RegExp("=" + db + "*([^\\]'\"]*?)" + db + "*\\]", "g"),
        mb = new RegExp(hb),
        nb = new RegExp("^" + fb + "$"),
        ob = {
            ID: new RegExp("^#(" + eb + ")"),
            CLASS: new RegExp("^\\.(" + eb + ")"),
            TAG: new RegExp("^(" + eb.replace("w", "w*") + ")"),
            ATTR: new RegExp("^" + gb),
            PSEUDO: new RegExp("^" + hb),
            CHILD: new RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\(" + db + "*(even|odd|(([+-]|)(\\d*)n|)" + db + "*(?:([+-]|)" + db + "*(\\d+)|))" + db + "*\\)|)", "i"),
            bool: new RegExp("^(?:" + cb + ")$", "i"),
            needsContext: new RegExp("^" + db + "*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\(" + db + "*((?:-\\d)?\\d*)" + db + "*\\)|)(?=[^-]|$)", "i")
        },
        pb = /^(?:input|select|textarea|button)$/i,
        qb = /^h\d$/i,
        rb = /^[^{]+\{\s*\[native \w/,
        sb = /^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/,
        tb = /[+~]/,
        ub = /'|\\/g,
        vb = new RegExp("\\\\([\\da-f]{1,6}" + db + "?|(" + db + ")|.)", "ig"),
        wb = function(a, b, c) {
            var d = "0x" + b - 65536;
            return d !== d || c ? b: 0 > d ? String.fromCharCode(d + 65536) : String.fromCharCode(d >> 10 | 55296, 1023 & d | 56320)
        };
        try {
            _.apply(Y = ab.call(O.childNodes), O.childNodes),
            Y[O.childNodes.length].nodeType
        } catch(xb) {
            _ = {
                apply: Y.length ?
                function(a, b) {
                    $.apply(a, ab.call(b))
                }: function(a, b) {
                    for (var c = a.length,
                    d = 0; a[c++] = b[d++];);
                    a.length = c - 1
                }
            }
        }
        v = b.support = {},
        y = b.isXML = function(a) {
            var b = a && (a.ownerDocument || a).documentElement;
            return b ? "HTML" !== b.nodeName: !1
        },
        F = b.setDocument = function(a) {
            var b, c = a ? a.ownerDocument || a: O,
            d = c.defaultView;
            return c !== G && 9 === c.nodeType && c.documentElement ? (G = c, H = c.documentElement, I = !y(c), d && d !== d.top && (d.addEventListener ? d.addEventListener("unload",
            function() {
                F()
            },
            !1) : d.attachEvent && d.attachEvent("onunload",
            function() {
                F()
            })), v.attributes = e(function(a) {
                return a.className = "i",
                !a.getAttribute("className")
            }), v.getElementsByTagName = e(function(a) {
                return a.appendChild(c.createComment("")),
                !a.getElementsByTagName("*").length
            }), v.getElementsByClassName = rb.test(c.getElementsByClassName) && e(function(a) {
                return a.innerHTML = "<div class='a'></div><div class='a i'></div>",
                a.firstChild.className = "i",
                2 === a.getElementsByClassName("i").length
            }), v.getById = e(function(a) {
                return H.appendChild(a).id = N,
                !c.getElementsByName || !c.getElementsByName(N).length
            }), v.getById ? (w.find.ID = function(a, b) {
                if (typeof b.getElementById !== V && I) {
                    var c = b.getElementById(a);
                    return c && c.parentNode ? [c] : []
                }
            },
            w.filter.ID = function(a) {
                var b = a.replace(vb, wb);
                return function(a) {
                    return a.getAttribute("id") === b
                }
            }) : (delete w.find.ID, w.filter.ID = function(a) {
                var b = a.replace(vb, wb);
                return function(a) {
                    var c = typeof a.getAttributeNode !== V && a.getAttributeNode("id");
                    return c && c.value === b
                }
            }), w.find.TAG = v.getElementsByTagName ?
            function(a, b) {
                return typeof b.getElementsByTagName !== V ? b.getElementsByTagName(a) : void 0
            }: function(a, b) {
                var c, d = [],
                e = 0,
                f = b.getElementsByTagName(a);
                if ("*" === a) {
                    for (; c = f[e++];) 1 === c.nodeType && d.push(c);
                    return d
                }
                return f
            },
            w.find.CLASS = v.getElementsByClassName &&
            function(a, b) {
                return typeof b.getElementsByClassName !== V && I ? b.getElementsByClassName(a) : void 0
            },
            K = [], J = [], (v.qsa = rb.test(c.querySelectorAll)) && (e(function(a) {
                a.innerHTML = "<select msallowclip=''><option selected=''></option></select>",
                a.querySelectorAll("[msallowclip^='']").length && J.push("[*^$]=" + db + "*(?:''|\"\")"),
                a.querySelectorAll("[selected]").length || J.push("\\[" + db + "*(?:value|" + cb + ")"),
                a.querySelectorAll(":checked").length || J.push(":checked")
            }), e(function(a) {
                var b = c.createElement("input");
                b.setAttribute("type", "hidden"),
                a.appendChild(b).setAttribute("name", "D"),
                a.querySelectorAll("[name=d]").length && J.push("name" + db + "*[*^$|!~]?="),
                a.querySelectorAll(":enabled").length || J.push(":enabled", ":disabled"),
                a.querySelectorAll("*,:x"),
                J.push(",.*:")
            })), (v.matchesSelector = rb.test(L = H.matches || H.webkitMatchesSelector || H.mozMatchesSelector || H.oMatchesSelector || H.msMatchesSelector)) && e(function(a) {
                v.disconnectedMatch = L.call(a, "div"),
                L.call(a, "[s!='']:x"),
                K.push("!=", hb)
            }), J = J.length && new RegExp(J.join("|")), K = K.length && new RegExp(K.join("|")), b = rb.test(H.compareDocumentPosition), M = b || rb.test(H.contains) ?
            function(a, b) {
                var c = 9 === a.nodeType ? a.documentElement: a,
                d = b && b.parentNode;
                return a === d || !(!d || 1 !== d.nodeType || !(c.contains ? c.contains(d) : a.compareDocumentPosition && 16 & a.compareDocumentPosition(d)))
            }: function(a, b) {
                if (b) for (; b = b.parentNode;) if (b === a) return ! 0;
                return ! 1
            },
            U = b ?
            function(a, b) {
                if (a === b) return E = !0,
                0;
                var d = !a.compareDocumentPosition - !b.compareDocumentPosition;
                return d ? d: (d = (a.ownerDocument || a) === (b.ownerDocument || b) ? a.compareDocumentPosition(b) : 1, 1 & d || !v.sortDetached && b.compareDocumentPosition(a) === d ? a === c || a.ownerDocument === O && M(O, a) ? -1 : b === c || b.ownerDocument === O && M(O, b) ? 1 : D ? bb.call(D, a) - bb.call(D, b) : 0 : 4 & d ? -1 : 1)
            }: function(a, b) {
                if (a === b) return E = !0,
                0;
                var d, e = 0,
                f = a.parentNode,
                h = b.parentNode,
                i = [a],
                j = [b];
                if (!f || !h) return a === c ? -1 : b === c ? 1 : f ? -1 : h ? 1 : D ? bb.call(D, a) - bb.call(D, b) : 0;
                if (f === h) return g(a, b);
                for (d = a; d = d.parentNode;) i.unshift(d);
                for (d = b; d = d.parentNode;) j.unshift(d);
                for (; i[e] === j[e];) e++;
                return e ? g(i[e], j[e]) : i[e] === O ? -1 : j[e] === O ? 1 : 0
            },
            c) : G
        },
        b.matches = function(a, c) {
            return b(a, null, null, c)
        },
        b.matchesSelector = function(a, c) {
            if ((a.ownerDocument || a) !== G && F(a), c = c.replace(lb, "='$1']"), !(!v.matchesSelector || !I || K && K.test(c) || J && J.test(c))) try {
                var d = L.call(a, c);
                if (d || v.disconnectedMatch || a.document && 11 !== a.document.nodeType) return d
            } catch(e) {}
            return b(c, G, null, [a]).length > 0
        },
        b.contains = function(a, b) {
            return (a.ownerDocument || a) !== G && F(a),
            M(a, b)
        },
        b.attr = function(a, b) { (a.ownerDocument || a) !== G && F(a);
            var c = w.attrHandle[b.toLowerCase()],
            d = c && X.call(w.attrHandle, b.toLowerCase()) ? c(a, b, !I) : void 0;
            return void 0 !== d ? d: v.attributes || !I ? a.getAttribute(b) : (d = a.getAttributeNode(b)) && d.specified ? d.value: null
        },
        b.error = function(a) {
            throw new Error("Syntax error, unrecognized expression: " + a)
        },
        b.uniqueSort = function(a) {
            var b, c = [],
            d = 0,
            e = 0;
            if (E = !v.detectDuplicates, D = !v.sortStable && a.slice(0), a.sort(U), E) {
                for (; b = a[e++];) b === a[e] && (d = c.push(e));
                for (; d--;) a.splice(c[d], 1)
            }
            return D = null,
            a
        },
        x = b.getText = function(a) {
            var b, c = "",
            d = 0,
            e = a.nodeType;
            if (e) {
                if (1 === e || 9 === e || 11 === e) {
                    if ("string" == typeof a.textContent) return a.textContent;
                    for (a = a.firstChild; a; a = a.nextSibling) c += x(a)
                } else if (3 === e || 4 === e) return a.nodeValue
            } else for (; b = a[d++];) c += x(b);
            return c
        },
        w = b.selectors = {
            cacheLength: 50,
            createPseudo: d,
            match: ob,
            attrHandle: {},
            find: {},
            relative: {
                ">": {
                    dir: "parentNode",
                    first: !0
                },
                " ": {
                    dir: "parentNode"
                },
                "+": {
                    dir: "previousSibling",
                    first: !0
                },
                "~": {
                    dir: "previousSibling"
                }
            },
            preFilter: {
                ATTR: function(a) {
                    return a[1] = a[1].replace(vb, wb),
                    a[3] = (a[3] || a[4] || a[5] || "").replace(vb, wb),
                    "~=" === a[2] && (a[3] = " " + a[3] + " "),
                    a.slice(0, 4)
                },
                CHILD: function(a) {
                    return a[1] = a[1].toLowerCase(),
                    "nth" === a[1].slice(0, 3) ? (a[3] || b.error(a[0]), a[4] = +(a[4] ? a[5] + (a[6] || 1) : 2 * ("even" === a[3] || "odd" === a[3])), a[5] = +(a[7] + a[8] || "odd" === a[3])) : a[3] && b.error(a[0]),
                    a
                },
                PSEUDO: function(a) {
                    var b, c = !a[6] && a[2];
                    return ob.CHILD.test(a[0]) ? null: (a[3] ? a[2] = a[4] || a[5] || "": c && mb.test(c) && (b = z(c, !0)) && (b = c.indexOf(")", c.length - b) - c.length) && (a[0] = a[0].slice(0, b), a[2] = c.slice(0, b)), a.slice(0, 3))
                }
            },
            filter: {
                TAG: function(a) {
                    var b = a.replace(vb, wb).toLowerCase();
                    return "*" === a ?
                    function() {
                        return ! 0
                    }: function(a) {
                        return a.nodeName && a.nodeName.toLowerCase() === b
                    }
                },
                CLASS: function(a) {
                    var b = R[a + " "];
                    return b || (b = new RegExp("(^|" + db + ")" + a + "(" + db + "|$)")) && R(a,
                    function(a) {
                        return b.test("string" == typeof a.className && a.className || typeof a.getAttribute !== V && a.getAttribute("class") || "")
                    })
                },
                ATTR: function(a, c, d) {
                    return function(e) {
                        var f = b.attr(e, a);
                        return null == f ? "!=" === c: c ? (f += "", "=" === c ? f === d: "!=" === c ? f !== d: "^=" === c ? d && 0 === f.indexOf(d) : "*=" === c ? d && f.indexOf(d) > -1 : "$=" === c ? d && f.slice( - d.length) === d: "~=" === c ? (" " + f + " ").indexOf(d) > -1 : "|=" === c ? f === d || f.slice(0, d.length + 1) === d + "-": !1) : !0
                    }
                },
                CHILD: function(a, b, c, d, e) {
                    var f = "nth" !== a.slice(0, 3),
                    g = "last" !== a.slice( - 4),
                    h = "of-type" === b;
                    return 1 === d && 0 === e ?
                    function(a) {
                        return !! a.parentNode
                    }: function(b, c, i) {
                        var j, k, l, m, n, o, p = f !== g ? "nextSibling": "previousSibling",
                        q = b.parentNode,
                        r = h && b.nodeName.toLowerCase(),
                        s = !i && !h;
                        if (q) {
                            if (f) {
                                for (; p;) {
                                    for (l = b; l = l[p];) if (h ? l.nodeName.toLowerCase() === r: 1 === l.nodeType) return ! 1;
                                    o = p = "only" === a && !o && "nextSibling"
                                }
                                return ! 0
                            }
                            if (o = [g ? q.firstChild: q.lastChild], g && s) {
                                for (k = q[N] || (q[N] = {}), j = k[a] || [], n = j[0] === P && j[1], m = j[0] === P && j[2], l = n && q.childNodes[n]; l = ++n && l && l[p] || (m = n = 0) || o.pop();) if (1 === l.nodeType && ++m && l === b) {
                                    k[a] = [P, n, m];
                                    break
                                }
                            } else if (s && (j = (b[N] || (b[N] = {}))[a]) && j[0] === P) m = j[1];
                            else for (; (l = ++n && l && l[p] || (m = n = 0) || o.pop()) && ((h ? l.nodeName.toLowerCase() !== r: 1 !== l.nodeType) || !++m || (s && ((l[N] || (l[N] = {}))[a] = [P, m]), l !== b)););
                            return m -= e,
                            m === d || m % d === 0 && m / d >= 0
                        }
                    }
                },
                PSEUDO: function(a, c) {
                    var e, f = w.pseudos[a] || w.setFilters[a.toLowerCase()] || b.error("unsupported pseudo: " + a);
                    return f[N] ? f(c) : f.length > 1 ? (e = [a, a, "", c], w.setFilters.hasOwnProperty(a.toLowerCase()) ? d(function(a, b) {
                        for (var d, e = f(a, c), g = e.length; g--;) d = bb.call(a, e[g]),
                        a[d] = !(b[d] = e[g])
                    }) : function(a) {
                        return f(a, 0, e)
                    }) : f
                }
            },
            pseudos: {
                not: d(function(a) {
                    var b = [],
                    c = [],
                    e = A(a.replace(ib, "$1"));
                    return e[N] ? d(function(a, b, c, d) {
                        for (var f, g = e(a, null, d, []), h = a.length; h--;)(f = g[h]) && (a[h] = !(b[h] = f))
                    }) : function(a, d, f) {
                        return b[0] = a,
                        e(b, null, f, c),
                        !c.pop()
                    }
                }),
                has: d(function(a) {
                    return function(c) {
                        return b(a, c).length > 0
                    }
                }),
                contains: d(function(a) {
                    return function(b) {
                        return (b.textContent || b.innerText || x(b)).indexOf(a) > -1
                    }
                }),
                lang: d(function(a) {
                    return nb.test(a || "") || b.error("unsupported lang: " + a),
                    a = a.replace(vb, wb).toLowerCase(),
                    function(b) {
                        var c;
                        do
                        if (c = I ? b.lang: b.getAttribute("xml:lang") || b.getAttribute("lang")) return c = c.toLowerCase(),
                        c === a || 0 === c.indexOf(a + "-");
                        while ((b = b.parentNode) && 1 === b.nodeType);
                        return ! 1
                    }
                }),
                target: function(b) {
                    var c = a.location && a.location.hash;
                    return c && c.slice(1) === b.id
                },
                root: function(a) {
                    return a === H
                },
                focus: function(a) {
                    return a === G.activeElement && (!G.hasFocus || G.hasFocus()) && !!(a.type || a.href || ~a.tabIndex)
                },
                enabled: function(a) {
                    return a.disabled === !1
                },
                disabled: function(a) {
                    return a.disabled === !0
                },
                checked: function(a) {
                    var b = a.nodeName.toLowerCase();
                    return "input" === b && !!a.checked || "option" === b && !!a.selected
                },
                selected: function(a) {
                    return a.parentNode && a.parentNode.selectedIndex,
                    a.selected === !0
                },
                empty: function(a) {
                    for (a = a.firstChild; a; a = a.nextSibling) if (a.nodeType < 6) return ! 1;
                    return ! 0
                },
                parent: function(a) {
                    return ! w.pseudos.empty(a)
                },
                header: function(a) {
                    return qb.test(a.nodeName)
                },
                input: function(a) {
                    return pb.test(a.nodeName)
                },
                button: function(a) {
                    var b = a.nodeName.toLowerCase();
                    return "input" === b && "button" === a.type || "button" === b
                },
                text: function(a) {
                    var b;
                    return "input" === a.nodeName.toLowerCase() && "text" === a.type && (null == (b = a.getAttribute("type")) || "text" === b.toLowerCase())
                },
                first: j(function() {
                    return [0]
                }),
                last: j(function(a, b) {
                    return [b - 1]
                }),
                eq: j(function(a, b, c) {
                    return [0 > c ? c + b: c]
                }),
                even: j(function(a, b) {
                    for (var c = 0; b > c; c += 2) a.push(c);
                    return a
                }),
                odd: j(function(a, b) {
                    for (var c = 1; b > c; c += 2) a.push(c);
                    return a
                }),
                lt: j(function(a, b, c) {
                    for (var d = 0 > c ? c + b: c; --d >= 0;) a.push(d);
                    return a
                }),
                gt: j(function(a, b, c) {
                    for (var d = 0 > c ? c + b: c; ++d < b;) a.push(d);
                    return a
                })
            }
        },
        w.pseudos.nth = w.pseudos.eq;
        for (u in {
            radio: !0,
            checkbox: !0,
            file: !0,
            password: !0,
            image: !0
        }) w.pseudos[u] = h(u);
        for (u in {
            submit: !0,
            reset: !0
        }) w.pseudos[u] = i(u);
        return l.prototype = w.filters = w.pseudos,
        w.setFilters = new l,
        z = b.tokenize = function(a, c) {
            var d, e, f, g, h, i, j, k = S[a + " "];
            if (k) return c ? 0 : k.slice(0);
            for (h = a, i = [], j = w.preFilter; h;) { (!d || (e = jb.exec(h))) && (e && (h = h.slice(e[0].length) || h), i.push(f = [])),
                d = !1,
                (e = kb.exec(h)) && (d = e.shift(), f.push({
                    value: d,
                    type: e[0].replace(ib, " ")
                }), h = h.slice(d.length));
                for (g in w.filter) ! (e = ob[g].exec(h)) || j[g] && !(e = j[g](e)) || (d = e.shift(), f.push({
                    value: d,
                    type: g,
                    matches: e
                }), h = h.slice(d.length));
                if (!d) break
            }
            return c ? h.length: h ? b.error(a) : S(a, i).slice(0)
        },
        A = b.compile = function(a, b) {
            var c, d = [],
            e = [],
            f = T[a + " "];
            if (!f) {
                for (b || (b = z(a)), c = b.length; c--;) f = s(b[c]),
                f[N] ? d.push(f) : e.push(f);
                f = T(a, t(e, d)),
                f.selector = a
            }
            return f
        },
        B = b.select = function(a, b, c, d) {
            var e, f, g, h, i, j = "function" == typeof a && a,
            l = !d && z(a = j.selector || a);
            if (c = c || [], 1 === l.length) {
                if (f = l[0] = l[0].slice(0), f.length > 2 && "ID" === (g = f[0]).type && v.getById && 9 === b.nodeType && I && w.relative[f[1].type]) {
                    if (b = (w.find.ID(g.matches[0].replace(vb, wb), b) || [])[0], !b) return c;
                    j && (b = b.parentNode),
                    a = a.slice(f.shift().value.length)
                }
                for (e = ob.needsContext.test(a) ? 0 : f.length; e--&&(g = f[e], !w.relative[h = g.type]);) if ((i = w.find[h]) && (d = i(g.matches[0].replace(vb, wb), tb.test(f[0].type) && k(b.parentNode) || b))) {
                    if (f.splice(e, 1), a = d.length && m(f), !a) return _.apply(c, d),
                    c;
                    break
                }
            }
            return (j || A(a, l))(d, b, !I, c, tb.test(a) && k(b.parentNode) || b),
            c
        },
        v.sortStable = N.split("").sort(U).join("") === N,
        v.detectDuplicates = !!E,
        F(),
        v.sortDetached = e(function(a) {
            return 1 & a.compareDocumentPosition(G.createElement("div"))
        }),
        e(function(a) {
            return a.innerHTML = "<a href='#'></a>",
            "#" === a.firstChild.getAttribute("href")
        }) || f("type|href|height|width",
        function(a, b, c) {
            return c ? void 0 : a.getAttribute(b, "type" === b.toLowerCase() ? 1 : 2)
        }),
        v.attributes && e(function(a) {
            return a.innerHTML = "<input/>",
            a.firstChild.setAttribute("value", ""),
            "" === a.firstChild.getAttribute("value")
        }) || f("value",
        function(a, b, c) {
            return c || "input" !== a.nodeName.toLowerCase() ? void 0 : a.defaultValue
        }),
        e(function(a) {
            return null == a.getAttribute("disabled")
        }) || f(cb,
        function(a, b, c) {
            var d;
            return c ? void 0 : a[b] === !0 ? b.toLowerCase() : (d = a.getAttributeNode(b)) && d.specified ? d.value: null
        }),
        b
    } (a); Bb.find = Gb, Bb.expr = Gb.selectors, Bb.expr[":"] = Bb.expr.pseudos, Bb.unique = Gb.uniqueSort, Bb.text = Gb.getText, Bb.isXMLDoc = Gb.isXML, Bb.contains = Gb.contains;
    var Hb = Bb.expr.match.needsContext,
    Ib = /^<(\w+)\s*\/?>(?:<\/\1>|)$/,
    Jb = /^.[^:#\[\.,]*$/; Bb.filter = function(a, b, c) {
        var d = b[0];
        return c && (a = ":not(" + a + ")"),
        1 === b.length && 1 === d.nodeType ? Bb.find.matchesSelector(d, a) ? [d] : [] : Bb.find.matches(a, Bb.grep(b,
        function(a) {
            return 1 === a.nodeType
        }))
    },
    Bb.fn.extend({
        find: function(a) {
            var b, c = [],
            d = this,
            e = d.length;
            if ("string" != typeof a) return this.pushStack(Bb(a).filter(function() {
                for (b = 0; e > b; b++) if (Bb.contains(d[b], this)) return ! 0
            }));
            for (b = 0; e > b; b++) Bb.find(a, d[b], c);
            return c = this.pushStack(e > 1 ? Bb.unique(c) : c),
            c.selector = this.selector ? this.selector + " " + a: a,
            c
        },
        filter: function(a) {
            return this.pushStack(d(this, a || [], !1))
        },
        not: function(a) {
            return this.pushStack(d(this, a || [], !0))
        },
        is: function(a) {
            return !! d(this, "string" == typeof a && Hb.test(a) ? Bb(a) : a || [], !1).length
        }
    });
    var Kb, Lb = a.document,
    Mb = /^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/,
    Nb = Bb.fn.init = function(a, b) {
        var c, d;
        if (!a) return this;
        if ("string" == typeof a) {
            if (c = "<" === a.charAt(0) && ">" === a.charAt(a.length - 1) && a.length >= 3 ? [null, a, null] : Mb.exec(a), !c || !c[1] && b) return ! b || b.jquery ? (b || Kb).find(a) : this.constructor(b).find(a);
            if (c[1]) {
                if (b = b instanceof Bb ? b[0] : b, Bb.merge(this, Bb.parseHTML(c[1], b && b.nodeType ? b.ownerDocument || b: Lb, !0)), Ib.test(c[1]) && Bb.isPlainObject(b)) for (c in b) Bb.isFunction(this[c]) ? this[c](b[c]) : this.attr(c, b[c]);
                return this
            }
            if (d = Lb.getElementById(c[2]), d && d.parentNode) {
                if (d.id !== c[2]) return Kb.find(a);
                this.length = 1,
                this[0] = d
            }
            return this.context = Lb,
            this.selector = a,
            this
        }
        return a.nodeType ? (this.context = this[0] = a, this.length = 1, this) : Bb.isFunction(a) ? "undefined" != typeof Kb.ready ? Kb.ready(a) : a(Bb) : (void 0 !== a.selector && (this.selector = a.selector, this.context = a.context), Bb.makeArray(a, this))
    }; Nb.prototype = Bb.fn, Kb = Bb(Lb);
    var Ob = /^(?:parents|prev(?:Until|All))/,
    Pb = {
        children: !0,
        contents: !0,
        next: !0,
        prev: !0
    }; Bb.extend({
        dir: function(a, b, c) {
            for (var d = [], e = a[b]; e && 9 !== e.nodeType && (void 0 === c || 1 !== e.nodeType || !Bb(e).is(c));) 1 === e.nodeType && d.push(e),
            e = e[b];
            return d
        },
        sibling: function(a, b) {
            for (var c = []; a; a = a.nextSibling) 1 === a.nodeType && a !== b && c.push(a);
            return c
        }
    }), Bb.fn.extend({
        has: function(a) {
            var b, c = Bb(a, this),
            d = c.length;
            return this.filter(function() {
                for (b = 0; d > b; b++) if (Bb.contains(this, c[b])) return ! 0
            })
        },
        closest: function(a, b) {
            for (var c, d = 0,
            e = this.length,
            f = [], g = Hb.test(a) || "string" != typeof a ? Bb(a, b || this.context) : 0; e > d; d++) for (c = this[d]; c && c !== b; c = c.parentNode) if (c.nodeType < 11 && (g ? g.index(c) > -1 : 1 === c.nodeType && Bb.find.matchesSelector(c, a))) {
                f.push(c);
                break
            }
            return this.pushStack(f.length > 1 ? Bb.unique(f) : f)
        },
        index: function(a) {
            return a ? "string" == typeof a ? Bb.inArray(this[0], Bb(a)) : Bb.inArray(a.jquery ? a[0] : a, this) : this[0] && this[0].parentNode ? this.first().prevAll().length: -1
        },
        add: function(a, b) {
            return this.pushStack(Bb.unique(Bb.merge(this.get(), Bb(a, b))))
        },
        addBack: function(a) {
            return this.add(null == a ? this.prevObject: this.prevObject.filter(a))
        }
    }), Bb.each({
        parent: function(a) {
            var b = a.parentNode;
            return b && 11 !== b.nodeType ? b: null
        },
        parents: function(a) {
            return Bb.dir(a, "parentNode")
        },
        parentsUntil: function(a, b, c) {
            return Bb.dir(a, "parentNode", c)
        },
        next: function(a) {
            return e(a, "nextSibling")
        },
        prev: function(a) {
            return e(a, "previousSibling")
        },
        nextAll: function(a) {
            return Bb.dir(a, "nextSibling")
        },
        prevAll: function(a) {
            return Bb.dir(a, "previousSibling")
        },
        nextUntil: function(a, b, c) {
            return Bb.dir(a, "nextSibling", c)
        },
        prevUntil: function(a, b, c) {
            return Bb.dir(a, "previousSibling", c)
        },
        siblings: function(a) {
            return Bb.sibling((a.parentNode || {}).firstChild, a)
        },
        children: function(a) {
            return Bb.sibling(a.firstChild)
        },
        contents: function(a) {
            return Bb.nodeName(a, "iframe") ? a.contentDocument || a.contentWindow.document: Bb.merge([], a.childNodes)
        }
    },
    function(a, b) {
        Bb.fn[a] = function(c, d) {
            var e = Bb.map(this, b, c);
            return "Until" !== a.slice( - 5) && (d = c),
            d && "string" == typeof d && (e = Bb.filter(d, e)),
            this.length > 1 && (Pb[a] || (e = Bb.unique(e)), Ob.test(a) && (e = e.reverse())),
            this.pushStack(e)
        }
    });
    var Qb = /\S+/g,
    Rb = {}; Bb.Callbacks = function(a) {
        a = "string" == typeof a ? Rb[a] || f(a) : Bb.extend({},
        a);
        var b, c, d, e, g, h, i = [],
        j = !a.once && [],
        k = function(f) {
            for (c = a.memory && f, d = !0, g = h || 0, h = 0, e = i.length, b = !0; i && e > g; g++) if (i[g].apply(f[0], f[1]) === !1 && a.stopOnFalse) {
                c = !1;
                break
            }
            b = !1,
            i && (j ? j.length && k(j.shift()) : c ? i = [] : l.disable())
        },
        l = {
            add: function() {
                if (i) {
                    var d = i.length; !
                    function f(b) {
                        Bb.each(b,
                        function(b, c) {
                            var d = Bb.type(c);
                            "function" === d ? a.unique && l.has(c) || i.push(c) : c && c.length && "string" !== d && f(c)
                        })
                    } (arguments),
                    b ? e = i.length: c && (h = d, k(c))
                }
                return this
            },
            remove: function() {
                return i && Bb.each(arguments,
                function(a, c) {
                    for (var d; (d = Bb.inArray(c, i, d)) > -1;) i.splice(d, 1),
                    b && (e >= d && e--, g >= d && g--)
                }),
                this
            },
            has: function(a) {
                return a ? Bb.inArray(a, i) > -1 : !(!i || !i.length)
            },
            empty: function() {
                return i = [],
                e = 0,
                this
            },
            disable: function() {
                return i = j = c = void 0,
                this
            },
            disabled: function() {
                return ! i
            },
            lock: function() {
                return j = void 0,
                c || l.disable(),
                this
            },
            locked: function() {
                return ! j
            },
            fireWith: function(a, c) {
                return ! i || d && !j || (c = c || [], c = [a, c.slice ? c.slice() : c], b ? j.push(c) : k(c)),
                this
            },
            fire: function() {
                return l.fireWith(this, arguments),
                this
            },
            fired: function() {
                return !! d
            }
        };
        return l
    },
    Bb.extend({
        Deferred: function(a) {
            var b = [["resolve", "done", Bb.Callbacks("once memory"), "resolved"], ["reject", "fail", Bb.Callbacks("once memory"), "rejected"], ["notify", "progress", Bb.Callbacks("memory")]],
            c = "pending",
            d = {
                state: function() {
                    return c
                },
                always: function() {
                    return e.done(arguments).fail(arguments),
                    this
                },
                then: function() {
                    var a = arguments;
                    return Bb.Deferred(function(c) {
                        Bb.each(b,
                        function(b, f) {
                            var g = Bb.isFunction(a[b]) && a[b];
                            e[f[1]](function() {
                                var a = g && g.apply(this, arguments);
                                a && Bb.isFunction(a.promise) ? a.promise().done(c.resolve).fail(c.reject).progress(c.notify) : c[f[0] + "With"](this === d ? c.promise() : this, g ? [a] : arguments)
                            })
                        }),
                        a = null
                    }).promise()
                },
                promise: function(a) {
                    return null != a ? Bb.extend(a, d) : d
                }
            },
            e = {};
            return d.pipe = d.then,
            Bb.each(b,
            function(a, f) {
                var g = f[2],
                h = f[3];
                d[f[1]] = g.add,
                h && g.add(function() {
                    c = h
                },
                b[1 ^ a][2].disable, b[2][2].lock),
                e[f[0]] = function() {
                    return e[f[0] + "With"](this === e ? d: this, arguments),
                    this
                },
                e[f[0] + "With"] = g.fireWith
            }),
            d.promise(e),
            a && a.call(e, e),
            e
        },
        when: function(a) {
            var b, c, d, e = 0,
            f = sb.call(arguments),
            g = f.length,
            h = 1 !== g || a && Bb.isFunction(a.promise) ? g: 0,
            i = 1 === h ? a: Bb.Deferred(),
            j = function(a, c, d) {
                return function(e) {
                    c[a] = this,
                    d[a] = arguments.length > 1 ? sb.call(arguments) : e,
                    d === b ? i.notifyWith(c, d) : --h || i.resolveWith(c, d)
                }
            };
            if (g > 1) for (b = new Array(g), c = new Array(g), d = new Array(g); g > e; e++) f[e] && Bb.isFunction(f[e].promise) ? f[e].promise().done(j(e, d, f)).fail(i.reject).progress(j(e, c, b)) : --h;
            return h || i.resolveWith(d, f),
            i.promise()
        }
    });
    var Sb; Bb.fn.ready = function(a) {
        return Bb.ready.promise().done(a),
        this
    },
    Bb.extend({
        isReady: !1,
        readyWait: 1,
        holdReady: function(a) {
            a ? Bb.readyWait++:Bb.ready(!0)
        },
        ready: function(a) {
            if (a === !0 ? !--Bb.readyWait: !Bb.isReady) {
                if (!Lb.body) return setTimeout(Bb.ready);
                Bb.isReady = !0,
                a !== !0 && --Bb.readyWait > 0 || (Sb.resolveWith(Lb, [Bb]), Bb.fn.triggerHandler && (Bb(Lb).triggerHandler("ready"), Bb(Lb).off("ready")))
            }
        }
    }), Bb.ready.promise = function(b) {
        if (!Sb) if (Sb = Bb.Deferred(), "complete" === Lb.readyState) setTimeout(Bb.ready);
        else if (Lb.addEventListener) Lb.addEventListener("DOMContentLoaded", h, !1),
        a.addEventListener("load", h, !1);
        else {
            Lb.attachEvent("onreadystatechange", h),
            a.attachEvent("onload", h);
            var c = !1;
            try {
                c = null == a.frameElement && Lb.documentElement
            } catch(d) {}
            c && c.doScroll && !
            function e() {
                if (!Bb.isReady) {
                    try {
                        c.doScroll("left")
                    } catch(a) {
                        return setTimeout(e, 50)
                    }
                    g(),
                    Bb.ready()
                }
            } ()
        }
        return Sb.promise(b)
    };
    var Tb, Ub = "undefined";
    for (Tb in Bb(zb)) break; zb.ownLast = "0" !== Tb, zb.inlineBlockNeedsLayout = !1, Bb(function() {
        var a, b, c, d;
        c = Lb.getElementsByTagName("body")[0],
        c && c.style && (b = Lb.createElement("div"), d = Lb.createElement("div"), d.style.cssText = "position:absolute;border:0;width:0;height:0;top:0;left:-9999px", c.appendChild(d).appendChild(b), typeof b.style.zoom !== Ub && (b.style.cssText = "display:inline;margin:0;border:0;padding:1px;width:1px;zoom:1", zb.inlineBlockNeedsLayout = a = 3 === b.offsetWidth, a && (c.style.zoom = 1)), c.removeChild(d))
    }),
    function() {
        var a = Lb.createElement("div");
        if (null == zb.deleteExpando) {
            zb.deleteExpando = !0;
            try {
                delete a.test
            } catch(b) {
                zb.deleteExpando = !1
            }
        }
        a = null
    } (), Bb.acceptData = function(a) {
        var b = Bb.noData[(a.nodeName + " ").toLowerCase()],
        c = +a.nodeType || 1;
        return 1 !== c && 9 !== c ? !1 : !b || b !== !0 && a.getAttribute("classid") === b
    };
    var Vb = /^(?:\{[\w\W]*\}|\[[\w\W]*\])$/,
    Wb = /([A-Z])/g; Bb.extend({
        cache: {},
        noData: {
            "applet ": !0,
            "embed ": !0,
            "object ": "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
        },
        hasData: function(a) {
            return a = a.nodeType ? Bb.cache[a[Bb.expando]] : a[Bb.expando],
            !!a && !j(a)
        },
        data: function(a, b, c) {
            return k(a, b, c)
        },
        removeData: function(a, b) {
            return l(a, b)
        },
        _data: function(a, b, c) {
            return k(a, b, c, !0)
        },
        _removeData: function(a, b) {
            return l(a, b, !0)
        }
    }), Bb.fn.extend({
        data: function(a, b) {
            var c, d, e, f = this[0],
            g = f && f.attributes;
            if (void 0 === a) {
                if (this.length && (e = Bb.data(f), 1 === f.nodeType && !Bb._data(f, "parsedAttrs"))) {
                    for (i(f, "options"), c = g.length; c--;) g[c] && (d = g[c].name, 0 === d.indexOf("data-") && (d = Bb.camelCase(d.slice(5)), "options" !== d && i(f, d, e[d])));
                    Bb._data(f, "parsedAttrs", !0)
                }
                return e
            }
            return Bb._data(f, "parsedAttrs") || Bb.data(f, Bb(f).data()),
            "object" == typeof a ? this.each(function() {
                Bb.data(this, a)
            }) : arguments.length > 1 ? this.each(function() {
                Bb.data(this, a, b)
            }) : f ? i(f, a, Bb.data(f, a)) : void 0
        },
        removeData: function(a) {
            return this.each(function() {
                Bb.removeData(this, a)
            })
        }
    }), Bb.extend({
        queue: function(a, b, c) {
            var d;
            return a ? (b = (b || "fx") + "queue", d = Bb._data(a, b), c && (!d || Bb.isArray(c) ? d = Bb._data(a, b, Bb.makeArray(c)) : d.push(c)), d || []) : void 0
        },
        dequeue: function(a, b) {
            b = b || "fx";
            var c = Bb.queue(a, b),
            d = c.length,
            e = c.shift(),
            f = Bb._queueHooks(a, b),
            g = function() {
                Bb.dequeue(a, b)
            };
            "inprogress" === e && (e = c.shift(), d--),
            e && ("fx" === b && c.unshift("inprogress"), delete f.stop, e.call(a, g, f)),
            !d && f && f.empty.fire()
        },
        _queueHooks: function(a, b) {
            var c = b + "queueHooks";
            return Bb._data(a, c) || Bb._data(a, c, {
                empty: Bb.Callbacks("once memory").add(function() {
                    Bb._removeData(a, b + "queue"),
                    Bb._removeData(a, c)
                })
            })
        }
    }), Bb.fn.extend({
        queue: function(a, b) {
            var c = 2;
            return "string" != typeof a && (b = a, a = "fx", c--),
            arguments.length < c ? Bb.queue(this[0], a) : void 0 === b ? this: this.each(function() {
                var c = Bb.queue(this, a, b);
                Bb._queueHooks(this, a),
                "fx" === a && "inprogress" !== c[0] && Bb.dequeue(this, a)
            })
        },
        dequeue: function(a) {
            return this.each(function() {
                Bb.dequeue(this, a)
            })
        },
        clearQueue: function(a) {
            return this.queue(a || "fx", [])
        },
        promise: function(a, b) {
            var c, d = 1,
            e = Bb.Deferred(),
            f = this,
            g = this.length,
            h = function() {--d || e.resolveWith(f, [f])
            };
            for ("string" != typeof a && (b = a, a = void 0), a = a || "fx"; g--;) c = Bb._data(f[g], a + "queueHooks"),
            c && c.empty && (d++, c.empty.add(h));
            return h(),
            e.promise(b)
        }
    });
    var Xb = /[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source,
    Yb = ["Top", "Right", "Bottom", "Left"], Zb = function(a, b) {
        return a = b || a,
        "none" === Bb.css(a, "display") || !Bb.contains(a.ownerDocument, a)
    },
    $b = Bb.access = function(a, b, c, d, e, f, g) {
        var h = 0,
        i = a.length,
        j = null == c;
        if ("object" === Bb.type(c)) {
            e = !0;
            for (h in c) Bb.access(a, b, h, c[h], !0, f, g)
        } else if (void 0 !== d && (e = !0, Bb.isFunction(d) || (g = !0), j && (g ? (b.call(a, d), b = null) : (j = b, b = function(a, b, c) {
            return j.call(Bb(a), c)
        })), b)) for (; i > h; h++) b(a[h], c, g ? d: d.call(a[h], h, b(a[h], c)));
        return e ? a: j ? b.call(a) : i ? b(a[0], c) : f
    },
    _b = /^(?:checkbox|radio)$/i; !
    function() {
        var a = Lb.createElement("input"),
        b = Lb.createElement("div"),
        c = Lb.createDocumentFragment();
        if (b.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", zb.leadingWhitespace = 3 === b.firstChild.nodeType, zb.tbody = !b.getElementsByTagName("tbody").length, zb.htmlSerialize = !!b.getElementsByTagName("link").length, zb.html5Clone = "<:nav></:nav>" !== Lb.createElement("nav").cloneNode(!0).outerHTML, a.type = "checkbox", a.checked = !0, c.appendChild(a), zb.appendChecked = a.checked, b.innerHTML = "<textarea>x</textarea>", zb.noCloneChecked = !!b.cloneNode(!0).lastChild.defaultValue, c.appendChild(b), b.innerHTML = "<input type='radio' checked='checked' name='t'/>", zb.checkClone = b.cloneNode(!0).cloneNode(!0).lastChild.checked, zb.noCloneEvent = !0, b.attachEvent && (b.attachEvent("onclick",
        function() {
            zb.noCloneEvent = !1
        }), b.cloneNode(!0).click()), null == zb.deleteExpando) {
            zb.deleteExpando = !0;
            try {
                delete b.test
            } catch(d) {
                zb.deleteExpando = !1
            }
        }
    } (),
    function() {
        var b, c, d = Lb.createElement("div");
        for (b in {
            submit: !0,
            change: !0,
            focusin: !0
        }) c = "on" + b,
        (zb[b + "Bubbles"] = c in a) || (d.setAttribute(c, "t"), zb[b + "Bubbles"] = d.attributes[c].expando === !1);
        d = null
    } ();
    var ac = /^(?:input|select|textarea)$/i,
    bc = /^key/,
    cc = /^(?:mouse|pointer|contextmenu)|click/,
    dc = /^(?:focusinfocus|focusoutblur)$/,
    ec = /^([^.]*)(?:\.(.+)|)$/; Bb.event = {
        global: {},
        add: function(a, b, c, d, e) {
            var f, g, h, i, j, k, l, m, n, o, p, q = Bb._data(a);
            if (q) {
                for (c.handler && (i = c, c = i.handler, e = i.selector), c.guid || (c.guid = Bb.guid++), (g = q.events) || (g = q.events = {}), (k = q.handle) || (k = q.handle = function(a) {
                    return typeof Bb === Ub || a && Bb.event.triggered === a.type ? void 0 : Bb.event.dispatch.apply(k.elem, arguments)
                },
                k.elem = a), b = (b || "").match(Qb) || [""], h = b.length; h--;) f = ec.exec(b[h]) || [],
                n = p = f[1],
                o = (f[2] || "").split(".").sort(),
                n && (j = Bb.event.special[n] || {},
                n = (e ? j.delegateType: j.bindType) || n, j = Bb.event.special[n] || {},
                l = Bb.extend({
                    type: n,
                    origType: p,
                    data: d,
                    handler: c,
                    guid: c.guid,
                    selector: e,
                    needsContext: e && Bb.expr.match.needsContext.test(e),
                    namespace: o.join(".")
                },
                i), (m = g[n]) || (m = g[n] = [], m.delegateCount = 0, j.setup && j.setup.call(a, d, o, k) !== !1 || (a.addEventListener ? a.addEventListener(n, k, !1) : a.attachEvent && a.attachEvent("on" + n, k))), j.add && (j.add.call(a, l), l.handler.guid || (l.handler.guid = c.guid)), e ? m.splice(m.delegateCount++, 0, l) : m.push(l), Bb.event.global[n] = !0);
                a = null
            }
        },
        remove: function(a, b, c, d, e) {
            var f, g, h, i, j, k, l, m, n, o, p, q = Bb.hasData(a) && Bb._data(a);
            if (q && (k = q.events)) {
                for (b = (b || "").match(Qb) || [""], j = b.length; j--;) if (h = ec.exec(b[j]) || [], n = p = h[1], o = (h[2] || "").split(".").sort(), n) {
                    for (l = Bb.event.special[n] || {},
                    n = (d ? l.delegateType: l.bindType) || n, m = k[n] || [], h = h[2] && new RegExp("(^|\\.)" + o.join("\\.(?:.*\\.|)") + "(\\.|$)"), i = f = m.length; f--;) g = m[f],
                    !e && p !== g.origType || c && c.guid !== g.guid || h && !h.test(g.namespace) || d && d !== g.selector && ("**" !== d || !g.selector) || (m.splice(f, 1), g.selector && m.delegateCount--, l.remove && l.remove.call(a, g));
                    i && !m.length && (l.teardown && l.teardown.call(a, o, q.handle) !== !1 || Bb.removeEvent(a, n, q.handle), delete k[n])
                } else for (n in k) Bb.event.remove(a, n + b[j], c, d, !0);
                Bb.isEmptyObject(k) && (delete q.handle, Bb._removeData(a, "events"))
            }
        },
        trigger: function(b, c, d, e) {
            var f, g, h, i, j, k, l, m = [d || Lb],
            n = yb.call(b, "type") ? b.type: b,
            o = yb.call(b, "namespace") ? b.namespace.split(".") : [];
            if (h = k = d = d || Lb, 3 !== d.nodeType && 8 !== d.nodeType && !dc.test(n + Bb.event.triggered) && (n.indexOf(".") >= 0 && (o = n.split("."), n = o.shift(), o.sort()), g = n.indexOf(":") < 0 && "on" + n, b = b[Bb.expando] ? b: new Bb.Event(n, "object" == typeof b && b), b.isTrigger = e ? 2 : 3, b.namespace = o.join("."), b.namespace_re = b.namespace ? new RegExp("(^|\\.)" + o.join("\\.(?:.*\\.|)") + "(\\.|$)") : null, b.result = void 0, b.target || (b.target = d), c = null == c ? [b] : Bb.makeArray(c, [b]), j = Bb.event.special[n] || {},
            e || !j.trigger || j.trigger.apply(d, c) !== !1)) {
                if (!e && !j.noBubble && !Bb.isWindow(d)) {
                    for (i = j.delegateType || n, dc.test(i + n) || (h = h.parentNode); h; h = h.parentNode) m.push(h),
                    k = h;
                    k === (d.ownerDocument || Lb) && m.push(k.defaultView || k.parentWindow || a)
                }
                for (l = 0; (h = m[l++]) && !b.isPropagationStopped();) b.type = l > 1 ? i: j.bindType || n,
                f = (Bb._data(h, "events") || {})[b.type] && Bb._data(h, "handle"),
                f && f.apply(h, c),
                f = g && h[g],
                f && f.apply && Bb.acceptData(h) && (b.result = f.apply(h, c), b.result === !1 && b.preventDefault());
                if (b.type = n, !e && !b.isDefaultPrevented() && (!j._default || j._default.apply(m.pop(), c) === !1) && Bb.acceptData(d) && g && d[n] && !Bb.isWindow(d)) {
                    k = d[g],
                    k && (d[g] = null),
                    Bb.event.triggered = n;
                    try {
                        d[n]()
                    } catch(p) {}
                    Bb.event.triggered = void 0,
                    k && (d[g] = k)
                }
                return b.result
            }
        },
        dispatch: function(a) {
            a = Bb.event.fix(a);
            var b, c, d, e, f, g = [],
            h = sb.call(arguments),
            i = (Bb._data(this, "events") || {})[a.type] || [],
            j = Bb.event.special[a.type] || {};
            if (h[0] = a, a.delegateTarget = this, !j.preDispatch || j.preDispatch.call(this, a) !== !1) {
                for (g = Bb.event.handlers.call(this, a, i), b = 0; (e = g[b++]) && !a.isPropagationStopped();) for (a.currentTarget = e.elem, f = 0; (d = e.handlers[f++]) && !a.isImmediatePropagationStopped();)(!a.namespace_re || a.namespace_re.test(d.namespace)) && (a.handleObj = d, a.data = d.data, c = ((Bb.event.special[d.origType] || {}).handle || d.handler).apply(e.elem, h), void 0 !== c && (a.result = c) === !1 && (a.preventDefault(), a.stopPropagation()));
                return j.postDispatch && j.postDispatch.call(this, a),
                a.result
            }
        },
        handlers: function(a, b) {
            var c, d, e, f, g = [],
            h = b.delegateCount,
            i = a.target;
            if (h && i.nodeType && (!a.button || "click" !== a.type)) for (; i != this; i = i.parentNode || this) if (1 === i.nodeType && (i.disabled !== !0 || "click" !== a.type)) {
                for (e = [], f = 0; h > f; f++) d = b[f],
                c = d.selector + " ",
                void 0 === e[c] && (e[c] = d.needsContext ? Bb(c, this).index(i) >= 0 : Bb.find(c, this, null, [i]).length),
                e[c] && e.push(d);
                e.length && g.push({
                    elem: i,
                    handlers: e
                })
            }
            return h < b.length && g.push({
                elem: this,
                handlers: b.slice(h)
            }),
            g
        },
        fix: function(a) {
            if (a[Bb.expando]) return a;
            var b, c, d, e = a.type,
            f = a,
            g = this.fixHooks[e];
            for (g || (this.fixHooks[e] = g = cc.test(e) ? this.mouseHooks: bc.test(e) ? this.keyHooks: {}), d = g.props ? this.props.concat(g.props) : this.props, a = new Bb.Event(f), b = d.length; b--;) c = d[b],
            a[c] = f[c];
            return a.target || (a.target = f.srcElement || Lb),
            3 === a.target.nodeType && (a.target = a.target.parentNode),
            a.metaKey = !!a.metaKey,
            g.filter ? g.filter(a, f) : a
        },
        props: "altKey bubbles cancelable ctrlKey currentTarget eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),
        fixHooks: {},
        keyHooks: {
            props: "char charCode key keyCode".split(" "),
            filter: function(a, b) {
                return null == a.which && (a.which = null != b.charCode ? b.charCode: b.keyCode),
                a
            }
        },
        mouseHooks: {
            props: "button buttons clientX clientY fromElement offsetX offsetY pageX pageY screenX screenY toElement".split(" "),
            filter: function(a, b) {
                var c, d, e, f = b.button,
                g = b.fromElement;
                return null == a.pageX && null != b.clientX && (d = a.target.ownerDocument || Lb, e = d.documentElement, c = d.body, a.pageX = b.clientX + (e && e.scrollLeft || c && c.scrollLeft || 0) - (e && e.clientLeft || c && c.clientLeft || 0), a.pageY = b.clientY + (e && e.scrollTop || c && c.scrollTop || 0) - (e && e.clientTop || c && c.clientTop || 0)),
                !a.relatedTarget && g && (a.relatedTarget = g === a.target ? b.toElement: g),
                a.which || void 0 === f || (a.which = 1 & f ? 1 : 2 & f ? 3 : 4 & f ? 2 : 0),
                a
            }
        },
        special: {
            load: {
                noBubble: !0
            },
            focus: {
                trigger: function() {
                    if (this !== o() && this.focus) try {
                        return this.focus(),
                        !1
                    } catch(a) {}
                },
                delegateType: "focusin"
            },
            blur: {
                trigger: function() {
                    return this === o() && this.blur ? (this.blur(), !1) : void 0
                },
                delegateType: "focusout"
            },
            click: {
                trigger: function() {
                    return Bb.nodeName(this, "input") && "checkbox" === this.type && this.click ? (this.click(), !1) : void 0
                },
                _default: function(a) {
                    return Bb.nodeName(a.target, "a")
                }
            },
            beforeunload: {
                postDispatch: function(a) {
                    void 0 !== a.result && a.originalEvent && (a.originalEvent.returnValue = a.result)
                }
            }
        },
        simulate: function(a, b, c, d) {
            var e = Bb.extend(new Bb.Event, c, {
                type: a,
                isSimulated: !0,
                originalEvent: {}
            });
            d ? Bb.event.trigger(e, null, b) : Bb.event.dispatch.call(b, e),
            e.isDefaultPrevented() && c.preventDefault()
        }
    },
    Bb.removeEvent = Lb.removeEventListener ?
    function(a, b, c) {
        a.removeEventListener && a.removeEventListener(b, c, !1)
    }: function(a, b, c) {
        var d = "on" + b;
        a.detachEvent && (typeof a[d] === Ub && (a[d] = null), a.detachEvent(d, c))
    },
    Bb.Event = function(a, b) {
        return this instanceof Bb.Event ? (a && a.type ? (this.originalEvent = a, this.type = a.type, this.isDefaultPrevented = a.defaultPrevented || void 0 === a.defaultPrevented && a.returnValue === !1 ? m: n) : this.type = a, b && Bb.extend(this, b), this.timeStamp = a && a.timeStamp || Bb.now(), void(this[Bb.expando] = !0)) : new Bb.Event(a, b)
    },
    Bb.Event.prototype = {
        isDefaultPrevented: n,
        isPropagationStopped: n,
        isImmediatePropagationStopped: n,
        preventDefault: function() {
            var a = this.originalEvent;
            this.isDefaultPrevented = m,
            a && (a.preventDefault ? a.preventDefault() : a.returnValue = !1)
        },
        stopPropagation: function() {
            var a = this.originalEvent;
            this.isPropagationStopped = m,
            a && (a.stopPropagation && a.stopPropagation(), a.cancelBubble = !0)
        },
        stopImmediatePropagation: function() {
            var a = this.originalEvent;
            this.isImmediatePropagationStopped = m,
            a && a.stopImmediatePropagation && a.stopImmediatePropagation(),
            this.stopPropagation()
        }
    },
    Bb.each({
        mouseenter: "mouseover",
        mouseleave: "mouseout",
        pointerenter: "pointerover",
        pointerleave: "pointerout"
    },
    function(a, b) {
        Bb.event.special[a] = {
            delegateType: b,
            bindType: b,
            handle: function(a) {
                var c, d = this,
                e = a.relatedTarget,
                f = a.handleObj;
                return (!e || e !== d && !Bb.contains(d, e)) && (a.type = f.origType, c = f.handler.apply(this, arguments), a.type = b),
                c
            }
        }
    }), zb.submitBubbles || (Bb.event.special.submit = {
        setup: function() {
            return Bb.nodeName(this, "form") ? !1 : void Bb.event.add(this, "click._submit keypress._submit",
            function(a) {
                var b = a.target,
                c = Bb.nodeName(b, "input") || Bb.nodeName(b, "button") ? b.form: void 0;
                c && !Bb._data(c, "submitBubbles") && (Bb.event.add(c, "submit._submit",
                function(a) {
                    a._submit_bubble = !0
                }), Bb._data(c, "submitBubbles", !0))
            })
        },
        postDispatch: function(a) {
            a._submit_bubble && (delete a._submit_bubble, this.parentNode && !a.isTrigger && Bb.event.simulate("submit", this.parentNode, a, !0))
        },
        teardown: function() {
            return Bb.nodeName(this, "form") ? !1 : void Bb.event.remove(this, "._submit")
        }
    }), zb.changeBubbles || (Bb.event.special.change = {
        setup: function() {
            return ac.test(this.nodeName) ? (("checkbox" === this.type || "radio" === this.type) && (Bb.event.add(this, "propertychange._change",
            function(a) {
                "checked" === a.originalEvent.propertyName && (this._just_changed = !0)
            }), Bb.event.add(this, "click._change",
            function(a) {
                this._just_changed && !a.isTrigger && (this._just_changed = !1),
                Bb.event.simulate("change", this, a, !0)
            })), !1) : void Bb.event.add(this, "beforeactivate._change",
            function(a) {
                var b = a.target;
                ac.test(b.nodeName) && !Bb._data(b, "changeBubbles") && (Bb.event.add(b, "change._change",
                function(a) { ! this.parentNode || a.isSimulated || a.isTrigger || Bb.event.simulate("change", this.parentNode, a, !0)
                }), Bb._data(b, "changeBubbles", !0))
            })
        },
        handle: function(a) {
            var b = a.target;
            return this !== b || a.isSimulated || a.isTrigger || "radio" !== b.type && "checkbox" !== b.type ? a.handleObj.handler.apply(this, arguments) : void 0
        },
        teardown: function() {
            return Bb.event.remove(this, "._change"),
            !ac.test(this.nodeName)
        }
    }), zb.focusinBubbles || Bb.each({
        focus: "focusin",
        blur: "focusout"
    },
    function(a, b) {
        var c = function(a) {
            Bb.event.simulate(b, a.target, Bb.event.fix(a), !0)
        };
        Bb.event.special[b] = {
            setup: function() {
                var d = this.ownerDocument || this,
                e = Bb._data(d, b);
                e || d.addEventListener(a, c, !0),
                Bb._data(d, b, (e || 0) + 1)
            },
            teardown: function() {
                var d = this.ownerDocument || this,
                e = Bb._data(d, b) - 1;
                e ? Bb._data(d, b, e) : (d.removeEventListener(a, c, !0), Bb._removeData(d, b))
            }
        }
    }), Bb.fn.extend({
        on: function(a, b, c, d, e) {
            var f, g;
            if ("object" == typeof a) {
                "string" != typeof b && (c = c || b, b = void 0);
                for (f in a) this.on(f, b, c, a[f], e);
                return this
            }
            if (null == c && null == d ? (d = b, c = b = void 0) : null == d && ("string" == typeof b ? (d = c, c = void 0) : (d = c, c = b, b = void 0)), d === !1) d = n;
            else if (!d) return this;
            return 1 === e && (g = d, d = function(a) {
                return Bb().off(a),
                g.apply(this, arguments)
            },
            d.guid = g.guid || (g.guid = Bb.guid++)),
            this.each(function() {
                Bb.event.add(this, a, d, c, b)
            })
        },
        one: function(a, b, c, d) {
            return this.on(a, b, c, d, 1)
        },
        off: function(a, b, c) {
            var d, e;
            if (a && a.preventDefault && a.handleObj) return d = a.handleObj,
            Bb(a.delegateTarget).off(d.namespace ? d.origType + "." + d.namespace: d.origType, d.selector, d.handler),
            this;
            if ("object" == typeof a) {
                for (e in a) this.off(e, b, a[e]);
                return this
            }
            return (b === !1 || "function" == typeof b) && (c = b, b = void 0),
            c === !1 && (c = n),
            this.each(function() {
                Bb.event.remove(this, a, c, b)
            })
        },
        trigger: function(a, b) {
            return this.each(function() {
                Bb.event.trigger(a, b, this)
            })
        },
        triggerHandler: function(a, b) {
            var c = this[0];
            return c ? Bb.event.trigger(a, b, c, !0) : void 0
        }
    });
    var fc = "abbr|article|aside|audio|bdi|canvas|data|datalist|details|figcaption|figure|footer|header|hgroup|mark|meter|nav|output|progress|section|summary|time|video",
    gc = / jQuery\d+="(?:null|\d+)"/g,
    hc = new RegExp("<(?:" + fc + ")[\\s/>]", "i"), ic = /^\s+/, jc = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi, kc = /<([\w:]+)/, lc = /<tbody/i, mc = /<|&#?\w+;/, nc = /<(?:script|style|link)/i, oc = /checked\s*(?:[^=]|=\s*.checked.)/i, pc = /^$|\/(?:java|ecma)script/i, qc = /^true\/(.*)/, rc = /^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g, sc = {
        option: [1, "<select multiple='multiple'>", "</select>"],
        legend: [1, "<fieldset>", "</fieldset>"],
        area: [1, "<map>", "</map>"],
        param: [1, "<object>", "</object>"],
        thead: [1, "<table>", "</table>"],
        tr: [2, "<table><tbody>", "</tbody></table>"],
        col: [2, "<table><tbody></tbody><colgroup>", "</colgroup></table>"],
        td: [3, "<table><tbody><tr>", "</tr></tbody></table>"],
        _default: zb.htmlSerialize ? [0, "", ""] : [1, "X<div>", "</div>"]
    },
    tc = p(Lb), uc = tc.appendChild(Lb.createElement("div")); sc.optgroup = sc.option, sc.tbody = sc.tfoot = sc.colgroup = sc.caption = sc.thead, sc.th = sc.td, Bb.extend({
        clone: function(a, b, c) {
            var d, e, f, g, h, i = Bb.contains(a.ownerDocument, a);
            if (zb.html5Clone || Bb.isXMLDoc(a) || !hc.test("<" + a.nodeName + ">") ? f = a.cloneNode(!0) : (uc.innerHTML = a.outerHTML, uc.removeChild(f = uc.firstChild)), !(zb.noCloneEvent && zb.noCloneChecked || 1 !== a.nodeType && 11 !== a.nodeType || Bb.isXMLDoc(a))) for (d = q(f), h = q(a), g = 0; null != (e = h[g]); ++g) d[g] && x(e, d[g]);
            if (b) if (c) for (h = h || q(a), d = d || q(f), g = 0; null != (e = h[g]); g++) w(e, d[g]);
            else w(a, f);
            return d = q(f, "script"),
            d.length > 0 && v(d, !i && q(a, "script")),
            d = h = e = null,
            f
        },
        buildFragment: function(a, b, c, d) {
            for (var e, f, g, h, i, j, k, l = a.length,
            m = p(b), n = [], o = 0; l > o; o++) if (f = a[o], f || 0 === f) if ("object" === Bb.type(f)) Bb.merge(n, f.nodeType ? [f] : f);
            else if (mc.test(f)) {
                for (h = h || m.appendChild(b.createElement("div")), i = (kc.exec(f) || ["", ""])[1].toLowerCase(), k = sc[i] || sc._default, h.innerHTML = k[1] + f.replace(jc, "<$1></$2>") + k[2], e = k[0]; e--;) h = h.lastChild;
                if (!zb.leadingWhitespace && ic.test(f) && n.push(b.createTextNode(ic.exec(f)[0])), !zb.tbody) for (f = "table" !== i || lc.test(f) ? "<table>" !== k[1] || lc.test(f) ? 0 : h: h.firstChild, e = f && f.childNodes.length; e--;) Bb.nodeName(j = f.childNodes[e], "tbody") && !j.childNodes.length && f.removeChild(j);
                for (Bb.merge(n, h.childNodes), h.textContent = ""; h.firstChild;) h.removeChild(h.firstChild);
                h = m.lastChild
            } else n.push(b.createTextNode(f));
            for (h && m.removeChild(h), zb.appendChecked || Bb.grep(q(n, "input"), r), o = 0; f = n[o++];) if ((!d || -1 === Bb.inArray(f, d)) && (g = Bb.contains(f.ownerDocument, f), h = q(m.appendChild(f), "script"), g && v(h), c)) for (e = 0; f = h[e++];) pc.test(f.type || "") && c.push(f);
            return h = null,
            m
        },
        cleanData: function(a, b) {
            for (var c, d, e, f, g = 0,
            h = Bb.expando,
            i = Bb.cache,
            j = zb.deleteExpando,
            k = Bb.event.special; null != (c = a[g]); g++) if ((b || Bb.acceptData(c)) && (e = c[h], f = e && i[e])) {
                if (f.events) for (d in f.events) k[d] ? Bb.event.remove(c, d) : Bb.removeEvent(c, d, f.handle);
                i[e] && (delete i[e], j ? delete c[h] : typeof c.removeAttribute !== Ub ? c.removeAttribute(h) : c[h] = null, rb.push(e))
            }
        }
    }), Bb.fn.extend({
        text: function(a) {
            return $b(this,
            function(a) {
                return void 0 === a ? Bb.text(this) : this.empty().append((this[0] && this[0].ownerDocument || Lb).createTextNode(a))
            },
            null, a, arguments.length)
        },
        append: function() {
            return this.domManip(arguments,
            function(a) {
                if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                    var b = s(this, a);
                    b.appendChild(a)
                }
            })
        },
        prepend: function() {
            return this.domManip(arguments,
            function(a) {
                if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                    var b = s(this, a);
                    b.insertBefore(a, b.firstChild)
                }
            })
        },
        before: function() {
            return this.domManip(arguments,
            function(a) {
                this.parentNode && this.parentNode.insertBefore(a, this)
            })
        },
        after: function() {
            return this.domManip(arguments,
            function(a) {
                this.parentNode && this.parentNode.insertBefore(a, this.nextSibling)
            })
        },
        remove: function(a, b) {
            for (var c, d = a ? Bb.filter(a, this) : this, e = 0; null != (c = d[e]); e++) b || 1 !== c.nodeType || Bb.cleanData(q(c)),
            c.parentNode && (b && Bb.contains(c.ownerDocument, c) && v(q(c, "script")), c.parentNode.removeChild(c));
            return this
        },
        empty: function() {
            for (var a, b = 0; null != (a = this[b]); b++) {
                for (1 === a.nodeType && Bb.cleanData(q(a, !1)); a.firstChild;) a.removeChild(a.firstChild);
                a.options && Bb.nodeName(a, "select") && (a.options.length = 0)
            }
            return this
        },
        clone: function(a, b) {
            return a = null == a ? !1 : a,
            b = null == b ? a: b,
            this.map(function() {
                return Bb.clone(this, a, b)
            })
        },
        html: function(a) {
            return $b(this,
            function(a) {
                var b = this[0] || {},
                c = 0,
                d = this.length;
                if (void 0 === a) return 1 === b.nodeType ? b.innerHTML.replace(gc, "") : void 0;
                if (! ("string" != typeof a || nc.test(a) || !zb.htmlSerialize && hc.test(a) || !zb.leadingWhitespace && ic.test(a) || sc[(kc.exec(a) || ["", ""])[1].toLowerCase()])) {
                    a = a.replace(jc, "<$1></$2>");
                    try {
                        for (; d > c; c++) b = this[c] || {},
                        1 === b.nodeType && (Bb.cleanData(q(b, !1)), b.innerHTML = a);
                        b = 0
                    } catch(e) {}
                }
                b && this.empty().append(a)
            },
            null, a, arguments.length)
        },
        replaceWith: function() {
            var a = arguments[0];
            return this.domManip(arguments,
            function(b) {
                a = this.parentNode,
                Bb.cleanData(q(this)),
                a && a.replaceChild(b, this)
            }),
            a && (a.length || a.nodeType) ? this: this.remove()
        },
        detach: function(a) {
            return this.remove(a, !0)
        },
        domManip: function(a, b) {
            a = tb.apply([], a);
            var c, d, e, f, g, h, i = 0,
            j = this.length,
            k = this,
            l = j - 1,
            m = a[0],
            n = Bb.isFunction(m);
            if (n || j > 1 && "string" == typeof m && !zb.checkClone && oc.test(m)) return this.each(function(c) {
                var d = k.eq(c);
                n && (a[0] = m.call(this, c, d.html())),
                d.domManip(a, b)
            });
            if (j && (h = Bb.buildFragment(a, this[0].ownerDocument, !1, this), c = h.firstChild, 1 === h.childNodes.length && (h = c), c)) {
                for (f = Bb.map(q(h, "script"), t), e = f.length; j > i; i++) d = h,
                i !== l && (d = Bb.clone(d, !0, !0), e && Bb.merge(f, q(d, "script"))),
                b.call(this[i], d, i);
                if (e) for (g = f[f.length - 1].ownerDocument, Bb.map(f, u), i = 0; e > i; i++) d = f[i],
                pc.test(d.type || "") && !Bb._data(d, "globalEval") && Bb.contains(g, d) && (d.src ? Bb._evalUrl && Bb._evalUrl(d.src) : Bb.globalEval((d.text || d.textContent || d.innerHTML || "").replace(rc, "")));
                h = c = null
            }
            return this
        }
    }), Bb.each({
        appendTo: "append",
        prependTo: "prepend",
        insertBefore: "before",
        insertAfter: "after",
        replaceAll: "replaceWith"
    },
    function(a, b) {
        Bb.fn[a] = function(a) {
            for (var c, d = 0,
            e = [], f = Bb(a), g = f.length - 1; g >= d; d++) c = d === g ? this: this.clone(!0),
            Bb(f[d])[b](c),
            ub.apply(e, c.get());
            return this.pushStack(e)
        }
    });
    var vc, wc = {}; !
    function() {
        var a;
        zb.shrinkWrapBlocks = function() {
            if (null != a) return a;
            a = !1;
            var b, c, d;
            return c = Lb.getElementsByTagName("body")[0],
            c && c.style ? (b = Lb.createElement("div"), d = Lb.createElement("div"), d.style.cssText = "position:absolute;border:0;width:0;height:0;top:0;left:-9999px", c.appendChild(d).appendChild(b), typeof b.style.zoom !== Ub && (b.style.cssText = "-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;margin:0;border:0;padding:1px;width:1px;zoom:1", b.appendChild(Lb.createElement("div")).style.width = "5px", a = 3 !== b.offsetWidth), c.removeChild(d), a) : void 0
        }
    } ();
    var xc, yc, zc = /^margin/,
    Ac = new RegExp("^(" + Xb + ")(?!px)[a-z%]+$", "i"), Bc = /^(top|right|bottom|left)$/; a.getComputedStyle ? (xc = function(a) {
        return a.ownerDocument.defaultView.getComputedStyle(a, null)
    },
    yc = function(a, b, c) {
        var d, e, f, g, h = a.style;
        return c = c || xc(a),
        g = c ? c.getPropertyValue(b) || c[b] : void 0,
        c && ("" !== g || Bb.contains(a.ownerDocument, a) || (g = Bb.style(a, b)), Ac.test(g) && zc.test(b) && (d = h.width, e = h.minWidth, f = h.maxWidth, h.minWidth = h.maxWidth = h.width = g, g = c.width, h.width = d, h.minWidth = e, h.maxWidth = f)),
        void 0 === g ? g: g + ""
    }) : Lb.documentElement.currentStyle && (xc = function(a) {
        return a.currentStyle
    },
    yc = function(a, b, c) {
        var d, e, f, g, h = a.style;
        return c = c || xc(a),
        g = c ? c[b] : void 0,
        null == g && h && h[b] && (g = h[b]),
        Ac.test(g) && !Bc.test(b) && (d = h.left, e = a.runtimeStyle, f = e && e.left, f && (e.left = a.currentStyle.left), h.left = "fontSize" === b ? "1em": g, g = h.pixelLeft + "px", h.left = d, f && (e.left = f)),
        void 0 === g ? g: g + "" || "auto"
    }),
    function() {
        function b() {
            var b, c, d, e;
            c = Lb.getElementsByTagName("body")[0],
            c && c.style && (b = Lb.createElement("div"), d = Lb.createElement("div"), d.style.cssText = "position:absolute;border:0;width:0;height:0;top:0;left:-9999px", c.appendChild(d).appendChild(b), b.style.cssText = "-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;display:block;margin-top:1%;top:1%;border:1px;padding:1px;width:4px;position:absolute", f = g = !1, i = !0, a.getComputedStyle && (f = "1%" !== (a.getComputedStyle(b, null) || {}).top, g = "4px" === (a.getComputedStyle(b, null) || {
                width: "4px"
            }).width, e = b.appendChild(Lb.createElement("div")), e.style.cssText = b.style.cssText = "-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;margin:0;border:0;padding:0", e.style.marginRight = e.style.width = "0", b.style.width = "1px", i = !parseFloat((a.getComputedStyle(e, null) || {}).marginRight)), b.innerHTML = "<table><tr><td></td><td>t</td></tr></table>", e = b.getElementsByTagName("td"), e[0].style.cssText = "margin:0;border:0;padding:0;display:none", h = 0 === e[0].offsetHeight, h && (e[0].style.display = "", e[1].style.display = "none", h = 0 === e[0].offsetHeight), c.removeChild(d))
        }
        var c, d, e, f, g, h, i;
        c = Lb.createElement("div"),
        c.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>",
        e = c.getElementsByTagName("a")[0],
        d = e && e.style,
        d && (d.cssText = "float:left;opacity:.5", zb.opacity = "0.5" === d.opacity, zb.cssFloat = !!d.cssFloat, c.style.backgroundClip = "content-box", c.cloneNode(!0).style.backgroundClip = "", zb.clearCloneStyle = "content-box" === c.style.backgroundClip, zb.boxSizing = "" === d.boxSizing || "" === d.MozBoxSizing || "" === d.WebkitBoxSizing, Bb.extend(zb, {
            reliableHiddenOffsets: function() {
                return null == h && b(),
                h
            },
            boxSizingReliable: function() {
                return null == g && b(),
                g
            },
            pixelPosition: function() {
                return null == f && b(),
                f
            },
            reliableMarginRight: function() {
                return null == i && b(),
                i
            }
        }))
    } (), Bb.swap = function(a, b, c, d) {
        var e, f, g = {};
        for (f in b) g[f] = a.style[f],
        a.style[f] = b[f];
        e = c.apply(a, d || []);
        for (f in b) a.style[f] = g[f];
        return e
    };
    var Cc = /alpha\([^)]*\)/i,
    Dc = /opacity\s*=\s*([^)]*)/,
    Ec = /^(none|table(?!-c[ea]).+)/,
    Fc = new RegExp("^(" + Xb + ")(.*)$", "i"), Gc = new RegExp("^([+-])=(" + Xb + ")", "i"), Hc = {
        position: "absolute",
        visibility: "hidden",
        display: "block"
    },
    Ic = {
        letterSpacing: "0",
        fontWeight: "400"
    },
    Jc = ["Webkit", "O", "Moz", "ms"]; Bb.extend({
        cssHooks: {
            opacity: {
                get: function(a, b) {
                    if (b) {
                        var c = yc(a, "opacity");
                        return "" === c ? "1": c
                    }
                }
            }
        },
        cssNumber: {
            columnCount: !0,
            fillOpacity: !0,
            flexGrow: !0,
            flexShrink: !0,
            fontWeight: !0,
            lineHeight: !0,
            opacity: !0,
            order: !0,
            orphans: !0,
            widows: !0,
            zIndex: !0,
            zoom: !0
        },
        cssProps: {
            "float": zb.cssFloat ? "cssFloat": "styleFloat"
        },
        style: function(a, b, c, d) {
            if (a && 3 !== a.nodeType && 8 !== a.nodeType && a.style) {
                var e, f, g, h = Bb.camelCase(b),
                i = a.style;
                if (b = Bb.cssProps[h] || (Bb.cssProps[h] = B(i, h)), g = Bb.cssHooks[b] || Bb.cssHooks[h], void 0 === c) return g && "get" in g && void 0 !== (e = g.get(a, !1, d)) ? e: i[b];
                if (f = typeof c, "string" === f && (e = Gc.exec(c)) && (c = (e[1] + 1) * e[2] + parseFloat(Bb.css(a, b)), f = "number"), null != c && c === c && ("number" !== f || Bb.cssNumber[h] || (c += "px"), zb.clearCloneStyle || "" !== c || 0 !== b.indexOf("background") || (i[b] = "inherit"), !(g && "set" in g && void 0 === (c = g.set(a, c, d))))) try {
                    i[b] = c
                } catch(j) {}
            }
        },
        css: function(a, b, c, d) {
            var e, f, g, h = Bb.camelCase(b);
            return b = Bb.cssProps[h] || (Bb.cssProps[h] = B(a.style, h)),
            g = Bb.cssHooks[b] || Bb.cssHooks[h],
            g && "get" in g && (f = g.get(a, !0, c)),
            void 0 === f && (f = yc(a, b, d)),
            "normal" === f && b in Ic && (f = Ic[b]),
            "" === c || c ? (e = parseFloat(f), c === !0 || Bb.isNumeric(e) ? e || 0 : f) : f
        }
    }), Bb.each(["height", "width"],
    function(a, b) {
        Bb.cssHooks[b] = {
            get: function(a, c, d) {
                return c ? Ec.test(Bb.css(a, "display")) && 0 === a.offsetWidth ? Bb.swap(a, Hc,
                function() {
                    return F(a, b, d)
                }) : F(a, b, d) : void 0
            },
            set: function(a, c, d) {
                var e = d && xc(a);
                return D(a, c, d ? E(a, b, d, zb.boxSizing && "border-box" === Bb.css(a, "boxSizing", !1, e), e) : 0)
            }
        }
    }), zb.opacity || (Bb.cssHooks.opacity = {
        get: function(a, b) {
            return Dc.test((b && a.currentStyle ? a.currentStyle.filter: a.style.filter) || "") ? .01 * parseFloat(RegExp.$1) + "": b ? "1": ""
        },
        set: function(a, b) {
            var c = a.style,
            d = a.currentStyle,
            e = Bb.isNumeric(b) ? "alpha(opacity=" + 100 * b + ")": "",
            f = d && d.filter || c.filter || "";
            c.zoom = 1,
            (b >= 1 || "" === b) && "" === Bb.trim(f.replace(Cc, "")) && c.removeAttribute && (c.removeAttribute("filter"), "" === b || d && !d.filter) || (c.filter = Cc.test(f) ? f.replace(Cc, e) : f + " " + e)
        }
    }), Bb.cssHooks.marginRight = A(zb.reliableMarginRight,
    function(a, b) {
        return b ? Bb.swap(a, {
            display: "inline-block"
        },
        yc, [a, "marginRight"]) : void 0
    }), Bb.each({
        margin: "",
        padding: "",
        border: "Width"
    },
    function(a, b) {
        Bb.cssHooks[a + b] = {
            expand: function(c) {
                for (var d = 0,
                e = {},
                f = "string" == typeof c ? c.split(" ") : [c]; 4 > d; d++) e[a + Yb[d] + b] = f[d] || f[d - 2] || f[0];
                return e
            }
        },
        zc.test(a) || (Bb.cssHooks[a + b].set = D)
    }), Bb.fn.extend({
        css: function(a, b) {
            return $b(this,
            function(a, b, c) {
                var d, e, f = {},
                g = 0;
                if (Bb.isArray(b)) {
                    for (d = xc(a), e = b.length; e > g; g++) f[b[g]] = Bb.css(a, b[g], !1, d);
                    return f
                }
                return void 0 !== c ? Bb.style(a, b, c) : Bb.css(a, b)
            },
            a, b, arguments.length > 1)
        },
        show: function() {
            return C(this, !0)
        },
        hide: function() {
            return C(this)
        },
        toggle: function(a) {
            return "boolean" == typeof a ? a ? this.show() : this.hide() : this.each(function() {
                Zb(this) ? Bb(this).show() : Bb(this).hide()
            })
        }
    }), Bb.Tween = G, G.prototype = {
        constructor: G,
        init: function(a, b, c, d, e, f) {
            this.elem = a,
            this.prop = c,
            this.easing = e || "swing",
            this.options = b,
            this.start = this.now = this.cur(),
            this.end = d,
            this.unit = f || (Bb.cssNumber[c] ? "": "px")
        },
        cur: function() {
            var a = G.propHooks[this.prop];
            return a && a.get ? a.get(this) : G.propHooks._default.get(this)
        },
        run: function(a) {
            var b, c = G.propHooks[this.prop];
            return this.pos = b = this.options.duration ? Bb.easing[this.easing](a, this.options.duration * a, 0, 1, this.options.duration) : a,
            this.now = (this.end - this.start) * b + this.start,
            this.options.step && this.options.step.call(this.elem, this.now, this),
            c && c.set ? c.set(this) : G.propHooks._default.set(this),
            this
        }
    },
    G.prototype.init.prototype = G.prototype, G.propHooks = {
        _default: {
            get: function(a) {
                var b;
                return null == a.elem[a.prop] || a.elem.style && null != a.elem.style[a.prop] ? (b = Bb.css(a.elem, a.prop, ""), b && "auto" !== b ? b: 0) : a.elem[a.prop]
            },
            set: function(a) {
                Bb.fx.step[a.prop] ? Bb.fx.step[a.prop](a) : a.elem.style && (null != a.elem.style[Bb.cssProps[a.prop]] || Bb.cssHooks[a.prop]) ? Bb.style(a.elem, a.prop, a.now + a.unit) : a.elem[a.prop] = a.now
            }
        }
    },
    G.propHooks.scrollTop = G.propHooks.scrollLeft = {
        set: function(a) {
            a.elem.nodeType && a.elem.parentNode && (a.elem[a.prop] = a.now)
        }
    },
    Bb.easing = {
        linear: function(a) {
            return a
        },
        swing: function(a) {
            return.5 - Math.cos(a * Math.PI) / 2
        }
    },
    Bb.fx = G.prototype.init, Bb.fx.step = {};
    var Kc, Lc, Mc = /^(?:toggle|show|hide)$/,
    Nc = new RegExp("^(?:([+-])=|)(" + Xb + ")([a-z%]*)$", "i"), Oc = /queueHooks$/, Pc = [K], Qc = {
        "*": [function(a, b) {
            var c = this.createTween(a, b),
            d = c.cur(),
            e = Nc.exec(b),
            f = e && e[3] || (Bb.cssNumber[a] ? "": "px"),
            g = (Bb.cssNumber[a] || "px" !== f && +d) && Nc.exec(Bb.css(c.elem, a)),
            h = 1,
            i = 20;
            if (g && g[3] !== f) {
                f = f || g[3],
                e = e || [],
                g = +d || 1;
                do h = h || ".5",
                g /= h,
                Bb.style(c.elem, a, g + f);
                while (h !== (h = c.cur() / d) && 1 !== h && --i)
            }
            return e && (g = c.start = +g || +d || 0, c.unit = f, c.end = e[1] ? g + (e[1] + 1) * e[2] : +e[2]),
            c
        }]
    }; Bb.Animation = Bb.extend(M, {
        tweener: function(a, b) {
            Bb.isFunction(a) ? (b = a, a = ["*"]) : a = a.split(" ");
            for (var c, d = 0,
            e = a.length; e > d; d++) c = a[d],
            Qc[c] = Qc[c] || [],
            Qc[c].unshift(b)
        },
        prefilter: function(a, b) {
            b ? Pc.unshift(a) : Pc.push(a)
        }
    }), Bb.speed = function(a, b, c) {
        var d = a && "object" == typeof a ? Bb.extend({},
        a) : {
            complete: c || !c && b || Bb.isFunction(a) && a,
            duration: a,
            easing: c && b || b && !Bb.isFunction(b) && b
        };
        return d.duration = Bb.fx.off ? 0 : "number" == typeof d.duration ? d.duration: d.duration in Bb.fx.speeds ? Bb.fx.speeds[d.duration] : Bb.fx.speeds._default,
        (null == d.queue || d.queue === !0) && (d.queue = "fx"),
        d.old = d.complete,
        d.complete = function() {
            Bb.isFunction(d.old) && d.old.call(this),
            d.queue && Bb.dequeue(this, d.queue)
        },
        d
    },
    Bb.fn.extend({
        fadeTo: function(a, b, c, d) {
            return this.filter(Zb).css("opacity", 0).show().end().animate({
                opacity: b
            },
            a, c, d)
        },
        animate: function(a, b, c, d) {
            var e = Bb.isEmptyObject(a),
            f = Bb.speed(b, c, d),
            g = function() {
                var b = M(this, Bb.extend({},
                a), f); (e || Bb._data(this, "finish")) && b.stop(!0)
            };
            return g.finish = g,
            e || f.queue === !1 ? this.each(g) : this.queue(f.queue, g)
        },
        stop: function(a, b, c) {
            var d = function(a) {
                var b = a.stop;
                delete a.stop,
                b(c)
            };
            return "string" != typeof a && (c = b, b = a, a = void 0),
            b && a !== !1 && this.queue(a || "fx", []),
            this.each(function() {
                var b = !0,
                e = null != a && a + "queueHooks",
                f = Bb.timers,
                g = Bb._data(this);
                if (e) g[e] && g[e].stop && d(g[e]);
                else for (e in g) g[e] && g[e].stop && Oc.test(e) && d(g[e]);
                for (e = f.length; e--;) f[e].elem !== this || null != a && f[e].queue !== a || (f[e].anim.stop(c), b = !1, f.splice(e, 1)); (b || !c) && Bb.dequeue(this, a)
            })
        },
        finish: function(a) {
            return a !== !1 && (a = a || "fx"),
            this.each(function() {
                var b, c = Bb._data(this),
                d = c[a + "queue"],
                e = c[a + "queueHooks"],
                f = Bb.timers,
                g = d ? d.length: 0;
                for (c.finish = !0, Bb.queue(this, a, []), e && e.stop && e.stop.call(this, !0), b = f.length; b--;) f[b].elem === this && f[b].queue === a && (f[b].anim.stop(!0), f.splice(b, 1));
                for (b = 0; g > b; b++) d[b] && d[b].finish && d[b].finish.call(this);
                delete c.finish
            })
        }
    }), Bb.each(["toggle", "show", "hide"],
    function(a, b) {
        var c = Bb.fn[b];
        Bb.fn[b] = function(a, d, e) {
            return null == a || "boolean" == typeof a ? c.apply(this, arguments) : this.animate(I(b, !0), a, d, e)
        }
    }), Bb.each({
        slideDown: I("show"),
        slideUp: I("hide"),
        slideToggle: I("toggle"),
        fadeIn: {
            opacity: "show"
        },
        fadeOut: {
            opacity: "hide"
        },
        fadeToggle: {
            opacity: "toggle"
        }
    },
    function(a, b) {
        Bb.fn[a] = function(a, c, d) {
            return this.animate(b, a, c, d)
        }
    }), Bb.timers = [], Bb.fx.tick = function() {
        var a, b = Bb.timers,
        c = 0;
        for (Kc = Bb.now(); c < b.length; c++) a = b[c],
        a() || b[c] !== a || b.splice(c--, 1);
        b.length || Bb.fx.stop(),
        Kc = void 0
    },
    Bb.fx.timer = function(a) {
        Bb.timers.push(a),
        a() ? Bb.fx.start() : Bb.timers.pop()
    },
    Bb.fx.interval = 13, Bb.fx.start = function() {
        Lc || (Lc = setInterval(Bb.fx.tick, Bb.fx.interval))
    },
    Bb.fx.stop = function() {
        clearInterval(Lc),
        Lc = null
    },
    Bb.fx.speeds = {
        slow: 600,
        fast: 200,
        _default: 400
    },
    Bb.fn.delay = function(a, b) {
        return a = Bb.fx ? Bb.fx.speeds[a] || a: a,
        b = b || "fx",
        this.queue(b,
        function(b, c) {
            var d = setTimeout(b, a);
            c.stop = function() {
                clearTimeout(d)
            }
        })
    },
    function() {
        var a, b, c, d, e;
        b = Lb.createElement("div"),
        b.setAttribute("className", "t"),
        b.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>",
        d = b.getElementsByTagName("a")[0],
        c = Lb.createElement("select"),
        e = c.appendChild(Lb.createElement("option")),
        a = b.getElementsByTagName("input")[0],
        d.style.cssText = "top:1px",
        zb.getSetAttribute = "t" !== b.className,
        zb.style = /top/.test(d.getAttribute("style")),
        zb.hrefNormalized = "/a" === d.getAttribute("href"),
        zb.checkOn = !!a.value,
        zb.optSelected = e.selected,
        zb.enctype = !!Lb.createElement("form").enctype,
        c.disabled = !0,
        zb.optDisabled = !e.disabled,
        a = Lb.createElement("input"),
        a.setAttribute("value", ""),
        zb.input = "" === a.getAttribute("value"),
        a.value = "t",
        a.setAttribute("type", "radio"),
        zb.radioValue = "t" === a.value
    } ();
    var Rc = /\r/g; Bb.fn.extend({
        val: function(a) {
            var b, c, d, e = this[0];
            return arguments.length ? (d = Bb.isFunction(a), this.each(function(c) {
                var e;
                1 === this.nodeType && (e = d ? a.call(this, c, Bb(this).val()) : a, null == e ? e = "": "number" == typeof e ? e += "": Bb.isArray(e) && (e = Bb.map(e,
                function(a) {
                    return null == a ? "": a + ""
                })), b = Bb.valHooks[this.type] || Bb.valHooks[this.nodeName.toLowerCase()], b && "set" in b && void 0 !== b.set(this, e, "value") || (this.value = e))
            })) : e ? (b = Bb.valHooks[e.type] || Bb.valHooks[e.nodeName.toLowerCase()], b && "get" in b && void 0 !== (c = b.get(e, "value")) ? c: (c = e.value, "string" == typeof c ? c.replace(Rc, "") : null == c ? "": c)) : void 0
        }
    }), Bb.extend({
        valHooks: {
            option: {
                get: function(a) {
                    var b = Bb.find.attr(a, "value");
                    return null != b ? b: Bb.trim(Bb.text(a))
                }
            },
            select: {
                get: function(a) {
                    for (var b, c, d = a.options,
                    e = a.selectedIndex,
                    f = "select-one" === a.type || 0 > e,
                    g = f ? null: [], h = f ? e + 1 : d.length, i = 0 > e ? h: f ? e: 0; h > i; i++) if (c = d[i], !(!c.selected && i !== e || (zb.optDisabled ? c.disabled: null !== c.getAttribute("disabled")) || c.parentNode.disabled && Bb.nodeName(c.parentNode, "optgroup"))) {
                        if (b = Bb(c).val(), f) return b;
                        g.push(b)
                    }
                    return g
                },
                set: function(a, b) {
                    for (var c, d, e = a.options,
                    f = Bb.makeArray(b), g = e.length; g--;) if (d = e[g], Bb.inArray(Bb.valHooks.option.get(d), f) >= 0) try {
                        d.selected = c = !0
                    } catch(h) {
                        d.scrollHeight
                    } else d.selected = !1;
                    return c || (a.selectedIndex = -1),
                    e
                }
            }
        }
    }), Bb.each(["radio", "checkbox"],
    function() {
        Bb.valHooks[this] = {
            set: function(a, b) {
                return Bb.isArray(b) ? a.checked = Bb.inArray(Bb(a).val(), b) >= 0 : void 0
            }
        },
        zb.checkOn || (Bb.valHooks[this].get = function(a) {
            return null === a.getAttribute("value") ? "on": a.value
        })
    });
    var Sc, Tc, Uc = Bb.expr.attrHandle,
    Vc = /^(?:checked|selected)$/i,
    Wc = zb.getSetAttribute,
    Xc = zb.input; Bb.fn.extend({
        attr: function(a, b) {
            return $b(this, Bb.attr, a, b, arguments.length > 1)
        },
        removeAttr: function(a) {
            return this.each(function() {
                Bb.removeAttr(this, a)
            })
        }
    }), Bb.extend({
        attr: function(a, b, c) {
            var d, e, f = a.nodeType;
            return a && 3 !== f && 8 !== f && 2 !== f ? typeof a.getAttribute === Ub ? Bb.prop(a, b, c) : (1 === f && Bb.isXMLDoc(a) || (b = b.toLowerCase(), d = Bb.attrHooks[b] || (Bb.expr.match.bool.test(b) ? Tc: Sc)), void 0 === c ? d && "get" in d && null !== (e = d.get(a, b)) ? e: (e = Bb.find.attr(a, b), null == e ? void 0 : e) : null !== c ? d && "set" in d && void 0 !== (e = d.set(a, c, b)) ? e: (a.setAttribute(b, c + ""), c) : void Bb.removeAttr(a, b)) : void 0
        },
        removeAttr: function(a, b) {
            var c, d, e = 0,
            f = b && b.match(Qb);
            if (f && 1 === a.nodeType) for (; c = f[e++];) d = Bb.propFix[c] || c,
            Bb.expr.match.bool.test(c) ? Xc && Wc || !Vc.test(c) ? a[d] = !1 : a[Bb.camelCase("default-" + c)] = a[d] = !1 : Bb.attr(a, c, ""),
            a.removeAttribute(Wc ? c: d)
        },
        attrHooks: {
            type: {
                set: function(a, b) {
                    if (!zb.radioValue && "radio" === b && Bb.nodeName(a, "input")) {
                        var c = a.value;
                        return a.setAttribute("type", b),
                        c && (a.value = c),
                        b
                    }
                }
            }
        }
    }), Tc = {
        set: function(a, b, c) {
            return b === !1 ? Bb.removeAttr(a, c) : Xc && Wc || !Vc.test(c) ? a.setAttribute(!Wc && Bb.propFix[c] || c, c) : a[Bb.camelCase("default-" + c)] = a[c] = !0,
            c
        }
    },
    Bb.each(Bb.expr.match.bool.source.match(/\w+/g),
    function(a, b) {
        var c = Uc[b] || Bb.find.attr;
        Uc[b] = Xc && Wc || !Vc.test(b) ?
        function(a, b, d) {
            var e, f;
            return d || (f = Uc[b], Uc[b] = e, e = null != c(a, b, d) ? b.toLowerCase() : null, Uc[b] = f),
            e
        }: function(a, b, c) {
            return c ? void 0 : a[Bb.camelCase("default-" + b)] ? b.toLowerCase() : null
        }
    }), Xc && Wc || (Bb.attrHooks.value = {
        set: function(a, b, c) {
            return Bb.nodeName(a, "input") ? void(a.defaultValue = b) : Sc && Sc.set(a, b, c)
        }
    }), Wc || (Sc = {
        set: function(a, b, c) {
            var d = a.getAttributeNode(c);
            return d || a.setAttributeNode(d = a.ownerDocument.createAttribute(c)),
            d.value = b += "",
            "value" === c || b === a.getAttribute(c) ? b: void 0
        }
    },
    Uc.id = Uc.name = Uc.coords = function(a, b, c) {
        var d;
        return c ? void 0 : (d = a.getAttributeNode(b)) && "" !== d.value ? d.value: null
    },
    Bb.valHooks.button = {
        get: function(a, b) {
            var c = a.getAttributeNode(b);
            return c && c.specified ? c.value: void 0
        },
        set: Sc.set
    },
    Bb.attrHooks.contenteditable = {
        set: function(a, b, c) {
            Sc.set(a, "" === b ? !1 : b, c)
        }
    },
    Bb.each(["width", "height"],
    function(a, b) {
        Bb.attrHooks[b] = {
            set: function(a, c) {
                return "" === c ? (a.setAttribute(b, "auto"), c) : void 0
            }
        }
    })), zb.style || (Bb.attrHooks.style = {
        get: function(a) {
            return a.style.cssText || void 0
        },
        set: function(a, b) {
            return a.style.cssText = b + ""
        }
    });
    var Yc = /^(?:input|select|textarea|button|object)$/i,
    Zc = /^(?:a|area)$/i; Bb.fn.extend({
        prop: function(a, b) {
            return $b(this, Bb.prop, a, b, arguments.length > 1)
        },
        removeProp: function(a) {
            return a = Bb.propFix[a] || a,
            this.each(function() {
                try {
                    this[a] = void 0,
                    delete this[a]
                } catch(b) {}
            })
        }
    }), Bb.extend({
        propFix: {
            "for": "htmlFor",
            "class": "className"
        },
        prop: function(a, b, c) {
            var d, e, f, g = a.nodeType;
            return a && 3 !== g && 8 !== g && 2 !== g ? (f = 1 !== g || !Bb.isXMLDoc(a), f && (b = Bb.propFix[b] || b, e = Bb.propHooks[b]), void 0 !== c ? e && "set" in e && void 0 !== (d = e.set(a, c, b)) ? d: a[b] = c: e && "get" in e && null !== (d = e.get(a, b)) ? d: a[b]) : void 0
        },
        propHooks: {
            tabIndex: {
                get: function(a) {
                    var b = Bb.find.attr(a, "tabindex");
                    return b ? parseInt(b, 10) : Yc.test(a.nodeName) || Zc.test(a.nodeName) && a.href ? 0 : -1
                }
            }
        }
    }), zb.hrefNormalized || Bb.each(["href", "src"],
    function(a, b) {
        Bb.propHooks[b] = {
            get: function(a) {
                return a.getAttribute(b, 4)
            }
        }
    }), zb.optSelected || (Bb.propHooks.selected = {
        get: function(a) {
            var b = a.parentNode;
            return b && (b.selectedIndex, b.parentNode && b.parentNode.selectedIndex),
            null
        }
    }), Bb.each(["tabIndex", "readOnly", "maxLength", "cellSpacing", "cellPadding", "rowSpan", "colSpan", "useMap", "frameBorder", "contentEditable"],
    function() {
        Bb.propFix[this.toLowerCase()] = this
    }), zb.enctype || (Bb.propFix.enctype = "encoding");
    var $c = /[\t\r\n\f]/g; Bb.fn.extend({
        addClass: function(a) {
            var b, c, d, e, f, g, h = 0,
            i = this.length,
            j = "string" == typeof a && a;
            if (Bb.isFunction(a)) return this.each(function(b) {
                Bb(this).addClass(a.call(this, b, this.className))
            });
            if (j) for (b = (a || "").match(Qb) || []; i > h; h++) if (c = this[h], d = 1 === c.nodeType && (c.className ? (" " + c.className + " ").replace($c, " ") : " ")) {
                for (f = 0; e = b[f++];) d.indexOf(" " + e + " ") < 0 && (d += e + " ");
                g = Bb.trim(d),
                c.className !== g && (c.className = g)
            }
            return this
        },
        removeClass: function(a) {
            var b, c, d, e, f, g, h = 0,
            i = this.length,
            j = 0 === arguments.length || "string" == typeof a && a;
            if (Bb.isFunction(a)) return this.each(function(b) {
                Bb(this).removeClass(a.call(this, b, this.className))
            });
            if (j) for (b = (a || "").match(Qb) || []; i > h; h++) if (c = this[h], d = 1 === c.nodeType && (c.className ? (" " + c.className + " ").replace($c, " ") : "")) {
                for (f = 0; e = b[f++];) for (; d.indexOf(" " + e + " ") >= 0;) d = d.replace(" " + e + " ", " ");
                g = a ? Bb.trim(d) : "",
                c.className !== g && (c.className = g)
            }
            return this
        },
        toggleClass: function(a, b) {
            var c = typeof a;
            return "boolean" == typeof b && "string" === c ? b ? this.addClass(a) : this.removeClass(a) : this.each(Bb.isFunction(a) ?
            function(c) {
                Bb(this).toggleClass(a.call(this, c, this.className, b), b)
            }: function() {
                if ("string" === c) for (var b, d = 0,
                e = Bb(this), f = a.match(Qb) || []; b = f[d++];) e.hasClass(b) ? e.removeClass(b) : e.addClass(b);
                else(c === Ub || "boolean" === c) && (this.className && Bb._data(this, "__className__", this.className), this.className = this.className || a === !1 ? "": Bb._data(this, "__className__") || "")
            })
        },
        hasClass: function(a) {
            for (var b = " " + a + " ",
            c = 0,
            d = this.length; d > c; c++) if (1 === this[c].nodeType && (" " + this[c].className + " ").replace($c, " ").indexOf(b) >= 0) return ! 0;
            return ! 1
        }
    }), Bb.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "),
    function(a, b) {
        Bb.fn[b] = function(a, c) {
            return arguments.length > 0 ? this.on(b, null, a, c) : this.trigger(b)
        }
    }), Bb.fn.extend({
        hover: function(a, b) {
            return this.mouseenter(a).mouseleave(b || a)
        },
        bind: function(a, b, c) {
            return this.on(a, null, b, c)
        },
        unbind: function(a, b) {
            return this.off(a, null, b)
        },
        delegate: function(a, b, c, d) {
            return this.on(b, a, c, d)
        },
        undelegate: function(a, b, c) {
            return 1 === arguments.length ? this.off(a, "**") : this.off(b, a || "**", c)
        }
    });
    var _c = Bb.now(), ad = /\?/, bd = /(,)|(\[|{)|(}|])|"(?:[^"\\\r\n]|\\["\\\/bfnrt]|\\u[\da-fA-F]{4})*"\s*:?|true|false|null|-?(?!0\d)\d+(?:\.\d+|)(?:[eE][+-]?\d+|)/g; Bb.parseJSON = function(b) {
        if (a.JSON && a.JSON.parse) return a.JSON.parse(b + "");
        var c, d = null,
        e = Bb.trim(b + "");
        return e && !Bb.trim(e.replace(bd,
        function(a, b, e, f) {
            return c && b && (d = 0),
            0 === d ? a: (c = e || b, d += !f - !e, "")
        })) ? Function("return " + e)() : Bb.error("Invalid JSON: " + b)
    },
    Bb.parseXML = function(b) {
        var c, d;
        if (!b || "string" != typeof b) return null;
        try {
            a.DOMParser ? (d = new DOMParser, c = d.parseFromString(b, "text/xml")) : (c = new ActiveXObject("Microsoft.XMLDOM"), c.async = "false", c.loadXML(b))
        } catch(e) {
            c = void 0
        }
        return c && c.documentElement && !c.getElementsByTagName("parsererror").length || Bb.error("Invalid XML: " + b),
        c
    };
    var cd, dd, ed = /#.*$/,
    fd = /([?&])_=[^&]*/,
    gd = /^(.*?):[ \t]*([^\r\n]*)\r?$/gm,
    hd = /^(?:about|app|app-storage|.+-extension|file|res|widget):$/,
    id = /^(?:GET|HEAD)$/,
    jd = /^\/\//,
    kd = /^([\w.+-]+:)(?:\/\/(?:[^\/?#]*@|)([^\/?#:]*)(?::(\d+)|)|)/,
    ld = {},
    md = {},
    nd = "*/".concat("*");
    try {
        dd = location.href
    } catch(od) {
        dd = Lb.createElement("a"),
        dd.href = "",
        dd = dd.href
    }
    cd = kd.exec(dd.toLowerCase()) || [], Bb.extend({
        active: 0,
        lastModified: {},
        etag: {},
        ajaxSettings: {
            url: dd,
            type: "GET",
            isLocal: hd.test(cd[1]),
            global: !0,
            processData: !0,
            async: !0,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            accepts: {
                "*": nd,
                text: "text/plain",
                html: "text/html",
                xml: "application/xml, text/xml",
                json: "application/json, text/javascript"
            },
            contents: {
                xml: /xml/,
                html: /html/,
                json: /json/
            },
            responseFields: {
                xml: "responseXML",
                text: "responseText",
                json: "responseJSON"
            },
            converters: {
                "* text": String,
                "text html": !0,
                "text json": Bb.parseJSON,
                "text xml": Bb.parseXML
            },
            flatOptions: {
                url: !0,
                context: !0
            }
        },
        ajaxSetup: function(a, b) {
            return b ? P(P(a, Bb.ajaxSettings), b) : P(Bb.ajaxSettings, a)
        },
        ajaxPrefilter: N(ld),
        ajaxTransport: N(md),
        ajax: function(a, b) {
            function c(a, b, c, d) {
                var e, k, r, s, u, w = b;
                2 !== t && (t = 2, h && clearTimeout(h), j = void 0, g = d || "", v.readyState = a > 0 ? 4 : 0, e = a >= 200 && 300 > a || 304 === a, c && (s = Q(l, v, c)), s = R(l, s, v, e), e ? (l.ifModified && (u = v.getResponseHeader("Last-Modified"), u && (Bb.lastModified[f] = u), u = v.getResponseHeader("etag"), u && (Bb.etag[f] = u)), 204 === a || "HEAD" === l.type ? w = "nocontent": 304 === a ? w = "notmodified": (w = s.state, k = s.data, r = s.error, e = !r)) : (r = w, (a || !w) && (w = "error", 0 > a && (a = 0))), v.status = a, v.statusText = (b || w) + "", e ? o.resolveWith(m, [k, w, v]) : o.rejectWith(m, [v, w, r]), v.statusCode(q), q = void 0, i && n.trigger(e ? "ajaxSuccess": "ajaxError", [v, l, e ? k: r]), p.fireWith(m, [v, w]), i && (n.trigger("ajaxComplete", [v, l]), --Bb.active || Bb.event.trigger("ajaxStop")))
            }
            "object" == typeof a && (b = a, a = void 0),
            b = b || {};
            var d, e, f, g, h, i, j, k, l = Bb.ajaxSetup({},
            b),
            m = l.context || l,
            n = l.context && (m.nodeType || m.jquery) ? Bb(m) : Bb.event,
            o = Bb.Deferred(),
            p = Bb.Callbacks("once memory"),
            q = l.statusCode || {},
            r = {},
            s = {},
            t = 0,
            u = "canceled",
            v = {
                readyState: 0,
                getResponseHeader: function(a) {
                    var b;
                    if (2 === t) {
                        if (!k) for (k = {}; b = gd.exec(g);) k[b[1].toLowerCase()] = b[2];
                        b = k[a.toLowerCase()]
                    }
                    return null == b ? null: b
                },
                getAllResponseHeaders: function() {
                    return 2 === t ? g: null
                },
                setRequestHeader: function(a, b) {
                    var c = a.toLowerCase();
                    return t || (a = s[c] = s[c] || a, r[a] = b),
                    this
                },
                overrideMimeType: function(a) {
                    return t || (l.mimeType = a),
                    this
                },
                statusCode: function(a) {
                    var b;
                    if (a) if (2 > t) for (b in a) q[b] = [q[b], a[b]];
                    else v.always(a[v.status]);
                    return this
                },
                abort: function(a) {
                    var b = a || u;
                    return j && j.abort(b),
                    c(0, b),
                    this
                }
            };
            if (o.promise(v).complete = p.add, v.success = v.done, v.error = v.fail, l.url = ((a || l.url || dd) + "").replace(ed, "").replace(jd, cd[1] + "//"), l.type = b.method || b.type || l.method || l.type, l.dataTypes = Bb.trim(l.dataType || "*").toLowerCase().match(Qb) || [""], null == l.crossDomain && (d = kd.exec(l.url.toLowerCase()), l.crossDomain = !(!d || d[1] === cd[1] && d[2] === cd[2] && (d[3] || ("http:" === d[1] ? "80": "443")) === (cd[3] || ("http:" === cd[1] ? "80": "443")))), l.data && l.processData && "string" != typeof l.data && (l.data = Bb.param(l.data, l.traditional)), O(ld, l, b, v), 2 === t) return v;
            i = l.global,
            i && 0 === Bb.active++&&Bb.event.trigger("ajaxStart"),
            l.type = l.type.toUpperCase(),
            l.hasContent = !id.test(l.type),
            f = l.url,
            l.hasContent || (l.data && (f = l.url += (ad.test(f) ? "&": "?") + l.data, delete l.data), l.cache === !1 && (l.url = fd.test(f) ? f.replace(fd, "$1_=" + _c++) : f + (ad.test(f) ? "&": "?") + "_=" + _c++)),
            l.ifModified && (Bb.lastModified[f] && v.setRequestHeader("If-Modified-Since", Bb.lastModified[f]), Bb.etag[f] && v.setRequestHeader("If-None-Match", Bb.etag[f])),
            (l.data && l.hasContent && l.contentType !== !1 || b.contentType) && v.setRequestHeader("Content-Type", l.contentType),
            v.setRequestHeader("Accept", l.dataTypes[0] && l.accepts[l.dataTypes[0]] ? l.accepts[l.dataTypes[0]] + ("*" !== l.dataTypes[0] ? ", " + nd + "; q=0.01": "") : l.accepts["*"]);
            for (e in l.headers) v.setRequestHeader(e, l.headers[e]);
            if (l.beforeSend && (l.beforeSend.call(m, v, l) === !1 || 2 === t)) return v.abort();
            u = "abort";
            for (e in {
                success: 1,
                error: 1,
                complete: 1
            }) v[e](l[e]);
            if (j = O(md, l, b, v)) {
                v.readyState = 1,
                i && n.trigger("ajaxSend", [v, l]),
                l.async && l.timeout > 0 && (h = setTimeout(function() {
                    v.abort("timeout")
                },
                l.timeout));
                try {
                    t = 1,
                    j.send(r, c)
                } catch(w) {
                    if (! (2 > t)) throw w;
                    c( - 1, w)
                }
            } else c( - 1, "No Transport");
            return v
        },
        getJSON: function(a, b, c) {
            return Bb.get(a, b, c, "json")
        },
        getScript: function(a, b) {
            return Bb.get(a, void 0, b, "script")
        }
    }), Bb.each(["get", "post"],
    function(a, b) {
        Bb[b] = function(a, c, d, e) {
            return Bb.isFunction(c) && (e = e || d, d = c, c = void 0),
            Bb.ajax({
                url: a,
                type: b,
                dataType: e,
                data: c,
                success: d
            })
        }
    }), Bb.each(["ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend"],
    function(a, b) {
        Bb.fn[b] = function(a) {
            return this.on(b, a)
        }
    }), Bb._evalUrl = function(a) {
        return Bb.ajax({
            url: a,
            type: "GET",
            dataType: "script",
            async: !1,
            global: !1,
            "throws": !0
        })
    },
    Bb.fn.extend({
        wrapAll: function(a) {
            if (Bb.isFunction(a)) return this.each(function(b) {
                Bb(this).wrapAll(a.call(this, b))
            });
            if (this[0]) {
                var b = Bb(a, this[0].ownerDocument).eq(0).clone(!0);
                this[0].parentNode && b.insertBefore(this[0]),
                b.map(function() {
                    for (var a = this; a.firstChild && 1 === a.firstChild.nodeType;) a = a.firstChild;
                    return a
                }).append(this)
            }
            return this
        },
        wrapInner: function(a) {
            return this.each(Bb.isFunction(a) ?
            function(b) {
                Bb(this).wrapInner(a.call(this, b))
            }: function() {
                var b = Bb(this),
                c = b.contents();
                c.length ? c.wrapAll(a) : b.append(a)
            })
        },
        wrap: function(a) {
            var b = Bb.isFunction(a);
            return this.each(function(c) {
                Bb(this).wrapAll(b ? a.call(this, c) : a)
            })
        },
        unwrap: function() {
            return this.parent().each(function() {
                Bb.nodeName(this, "body") || Bb(this).replaceWith(this.childNodes)
            }).end()
        }
    }), Bb.expr.filters.hidden = function(a) {
        return a.offsetWidth <= 0 && a.offsetHeight <= 0 || !zb.reliableHiddenOffsets() && "none" === (a.style && a.style.display || Bb.css(a, "display"))
    },
    Bb.expr.filters.visible = function(a) {
        return ! Bb.expr.filters.hidden(a)
    };
    var pd = /%20/g,
    qd = /\[\]$/,
    rd = /\r?\n/g,
    sd = /^(?:submit|button|image|reset|file)$/i,
    td = /^(?:input|select|textarea|keygen)/i; Bb.param = function(a, b) {
        var c, d = [],
        e = function(a, b) {
            b = Bb.isFunction(b) ? b() : null == b ? "": b,
            d[d.length] = encodeURIComponent(a) + "=" + encodeURIComponent(b)
        };
        if (void 0 === b && (b = Bb.ajaxSettings && Bb.ajaxSettings.traditional), Bb.isArray(a) || a.jquery && !Bb.isPlainObject(a)) Bb.each(a,
        function() {
            e(this.name, this.value)
        });
        else for (c in a) S(c, a[c], b, e);
        return d.join("&").replace(pd, "+")
    },
    Bb.fn.extend({
        serialize: function() {
            return Bb.param(this.serializeArray())
        },
        serializeArray: function() {
            return this.map(function() {
                var a = Bb.prop(this, "elements");
                return a ? Bb.makeArray(a) : this
            }).filter(function() {
                var a = this.type;
                return this.name && !Bb(this).is(":disabled") && td.test(this.nodeName) && !sd.test(a) && (this.checked || !_b.test(a))
            }).map(function(a, b) {
                var c = Bb(this).val();
                return null == c ? null: Bb.isArray(c) ? Bb.map(c,
                function(a) {
                    return {
                        name: b.name,
                        value: a.replace(rd, "\r\n")
                    }
                }) : {
                    name: b.name,
                    value: c.replace(rd, "\r\n")
                }
            }).get()
        }
    }), Bb.ajaxSettings.xhr = void 0 !== a.ActiveXObject ?
    function() {
        return ! this.isLocal && /^(get|post|head|put|delete|options)$/i.test(this.type) && T() || U()
    }: T;
    var ud = 0,
    vd = {},
    wd = Bb.ajaxSettings.xhr(); a.ActiveXObject && Bb(a).on("unload",
    function() {
        for (var a in vd) vd[a](void 0, !0)
    }), zb.cors = !!wd && "withCredentials" in wd, wd = zb.ajax = !!wd, wd && Bb.ajaxTransport(function(a) {
        if (!a.crossDomain || zb.cors) {
            var b;
            return {
                send: function(c, d) {
                    var e, f = a.xhr(),
                    g = ++ud;
                    if (f.open(a.type, a.url, a.async, a.username, a.password), a.xhrFields) for (e in a.xhrFields) f[e] = a.xhrFields[e];
                    a.mimeType && f.overrideMimeType && f.overrideMimeType(a.mimeType),
                    a.crossDomain || c["X-Requested-With"] || (c["X-Requested-With"] = "XMLHttpRequest");
                    for (e in c) void 0 !== c[e] && f.setRequestHeader(e, c[e] + "");
                    f.send(a.hasContent && a.data || null),
                    b = function(c, e) {
                        var h, i, j;
                        if (b && (e || 4 === f.readyState)) if (delete vd[g], b = void 0, f.onreadystatechange = Bb.noop, e) 4 !== f.readyState && f.abort();
                        else {
                            j = {},
                            h = f.status,
                            "string" == typeof f.responseText && (j.text = f.responseText);
                            try {
                                i = f.statusText
                            } catch(k) {
                                i = ""
                            }
                            h || !a.isLocal || a.crossDomain ? 1223 === h && (h = 204) : h = j.text ? 200 : 404
                        }
                        j && d(h, i, j, f.getAllResponseHeaders())
                    },
                    a.async ? 4 === f.readyState ? setTimeout(b) : f.onreadystatechange = vd[g] = b: b()
                },
                abort: function() {
                    b && b(void 0, !0)
                }
            }
        }
    }), Bb.ajaxSetup({
        accepts: {
            script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"
        },
        contents: {
            script: /(?:java|ecma)script/
        },
        converters: {
            "text script": function(a) {
                return Bb.globalEval(a),
                a
            }
        }
    }), Bb.ajaxPrefilter("script",
    function(a) {
        void 0 === a.cache && (a.cache = !1),
        a.crossDomain && (a.type = "GET", a.global = !1)
    }), Bb.ajaxTransport("script",
    function(a) {
        if (a.crossDomain) {
            var b, c = Lb.head || Bb("head")[0] || Lb.documentElement;
            return {
                send: function(d, e) {
                    b = Lb.createElement("script"),
                    b.async = !0,
                    a.scriptCharset && (b.charset = a.scriptCharset),
                    b.src = a.url,
                    b.onload = b.onreadystatechange = function(a, c) { (c || !b.readyState || /loaded|complete/.test(b.readyState)) && (b.onload = b.onreadystatechange = null, b.parentNode && b.parentNode.removeChild(b), b = null, c || e(200, "success"))
                    },
                    c.insertBefore(b, c.firstChild)
                },
                abort: function() {
                    b && b.onload(void 0, !0)
                }
            }
        }
    });
    var xd = [], yd = /(=)\?(?=&|$)|\?\?/; Bb.ajaxSetup({
        jsonp: "callback",
        jsonpCallback: function() {
            var a = xd.pop() || Bb.expando + "_" + _c++;
            return this[a] = !0,
            a
        }
    }), Bb.ajaxPrefilter("json jsonp",
    function(b, c, d) {
        var e, f, g, h = b.jsonp !== !1 && (yd.test(b.url) ? "url": "string" == typeof b.data && !(b.contentType || "").indexOf("application/x-www-form-urlencoded") && yd.test(b.data) && "data");
        return h || "jsonp" === b.dataTypes[0] ? (e = b.jsonpCallback = Bb.isFunction(b.jsonpCallback) ? b.jsonpCallback() : b.jsonpCallback, h ? b[h] = b[h].replace(yd, "$1" + e) : b.jsonp !== !1 && (b.url += (ad.test(b.url) ? "&": "?") + b.jsonp + "=" + e), b.converters["script json"] = function() {
            return g || Bb.error(e + " was not called"),
            g[0]
        },
        b.dataTypes[0] = "json", f = a[e], a[e] = function() {
            g = arguments
        },
        d.always(function() {
            a[e] = f,
            b[e] && (b.jsonpCallback = c.jsonpCallback, xd.push(e)),
            g && Bb.isFunction(f) && f(g[0]),
            g = f = void 0
        }), "script") : void 0
    }), Bb.parseHTML = function(a, b, c) {
        if (!a || "string" != typeof a) return null;
        "boolean" == typeof b && (c = b, b = !1),
        b = b || Lb;
        var d = Ib.exec(a),
        e = !c && [];
        return d ? [b.createElement(d[1])] : (d = Bb.buildFragment([a], b, e), e && e.length && Bb(e).remove(), Bb.merge([], d.childNodes))
    };
    var zd = Bb.fn.load; Bb.fn.load = function(a, b, c) {
        if ("string" != typeof a && zd) return zd.apply(this, arguments);
        var d, e, f, g = this,
        h = a.indexOf(" ");
        return h >= 0 && (d = Bb.trim(a.slice(h, a.length)), a = a.slice(0, h)),
        Bb.isFunction(b) ? (c = b, b = void 0) : b && "object" == typeof b && (f = "POST"),
        g.length > 0 && Bb.ajax({
            url: a,
            type: f,
            dataType: "html",
            data: b
        }).done(function(a) {
            e = arguments,
            g.html(d ? Bb("<div>").append(Bb.parseHTML(a)).find(d) : a)
        }).complete(c &&
        function(a, b) {
            g.each(c, e || [a.responseText, b, a])
        }),
        this
    },
    Bb.expr.filters.animated = function(a) {
        return Bb.grep(Bb.timers,
        function(b) {
            return a === b.elem
        }).length
    };
    var Ad = a.document.documentElement; Bb.offset = {
        setOffset: function(a, b, c) {
            var d, e, f, g, h, i, j, k = Bb.css(a, "position"),
            l = Bb(a),
            m = {};
            "static" === k && (a.style.position = "relative"),
            h = l.offset(),
            f = Bb.css(a, "top"),
            i = Bb.css(a, "left"),
            j = ("absolute" === k || "fixed" === k) && Bb.inArray("auto", [f, i]) > -1,
            j ? (d = l.position(), g = d.top, e = d.left) : (g = parseFloat(f) || 0, e = parseFloat(i) || 0),
            Bb.isFunction(b) && (b = b.call(a, c, h)),
            null != b.top && (m.top = b.top - h.top + g),
            null != b.left && (m.left = b.left - h.left + e),
            "using" in b ? b.using.call(a, m) : l.css(m)
        }
    },
    Bb.fn.extend({
        offset: function(a) {
            if (arguments.length) return void 0 === a ? this: this.each(function(b) {
                Bb.offset.setOffset(this, a, b)
            });
            var b, c, d = {
                top: 0,
                left: 0
            },
            e = this[0],
            f = e && e.ownerDocument;
            return f ? (b = f.documentElement, Bb.contains(b, e) ? (typeof e.getBoundingClientRect !== Ub && (d = e.getBoundingClientRect()), c = V(f), {
                top: d.top + (c.pageYOffset || b.scrollTop) - (b.clientTop || 0),
                left: d.left + (c.pageXOffset || b.scrollLeft) - (b.clientLeft || 0)
            }) : d) : void 0
        },
        position: function() {
            if (this[0]) {
                var a, b, c = {
                    top: 0,
                    left: 0
                },
                d = this[0];
                return "fixed" === Bb.css(d, "position") ? b = d.getBoundingClientRect() : (a = this.offsetParent(), b = this.offset(), Bb.nodeName(a[0], "html") || (c = a.offset()), c.top += Bb.css(a[0], "borderTopWidth", !0), c.left += Bb.css(a[0], "borderLeftWidth", !0)),
                {
                    top: b.top - c.top - Bb.css(d, "marginTop", !0),
                    left: b.left - c.left - Bb.css(d, "marginLeft", !0)
                }
            }
        },
        offsetParent: function() {
            return this.map(function() {
                for (var a = this.offsetParent || Ad; a && !Bb.nodeName(a, "html") && "static" === Bb.css(a, "position");) a = a.offsetParent;
                return a || Ad
            })
        }
    }), Bb.each({
        scrollLeft: "pageXOffset",
        scrollTop: "pageYOffset"
    },
    function(a, b) {
        var c = /Y/.test(b);
        Bb.fn[a] = function(d) {
            return $b(this,
            function(a, d, e) {
                var f = V(a);
                return void 0 === e ? f ? b in f ? f[b] : f.document.documentElement[d] : a[d] : void(f ? f.scrollTo(c ? Bb(f).scrollLeft() : e, c ? e: Bb(f).scrollTop()) : a[d] = e)
            },
            a, d, arguments.length, null)
        }
    }), Bb.each(["top", "left"],
    function(a, b) {
        Bb.cssHooks[b] = A(zb.pixelPosition,
        function(a, c) {
            return c ? (c = yc(a, b), Ac.test(c) ? Bb(a).position()[b] + "px": c) : void 0
        })
    }), Bb.each({
        Height: "height",
        Width: "width"
    },
    function(a, b) {
        Bb.each({
            padding: "inner" + a,
            content: b,
            "": "outer" + a
        },
        function(c, d) {
            Bb.fn[d] = function(d, e) {
                var f = arguments.length && (c || "boolean" != typeof d),
                g = c || (d === !0 || e === !0 ? "margin": "border");
                return $b(this,
                function(b, c, d) {
                    var e;
                    return Bb.isWindow(b) ? b.document.documentElement["client" + a] : 9 === b.nodeType ? (e = b.documentElement, Math.max(b.body["scroll" + a], e["scroll" + a], b.body["offset" + a], e["offset" + a], e["client" + a])) : void 0 === d ? Bb.css(b, c, g) : Bb.style(b, c, d, g)
                },
                b, f ? d: void 0, f, null)
            }
        })
    }), Bb.fn.size = function() {
        return this.length
    },
    Bb.fn.andSelf = Bb.fn.addBack, Bb.extend({
        BAR_IS_LOADED: !1,
        showLoading: function() {
            if (!this.BAR_IS_LOADED) {
                this.BAR_IS_LOADED = !0;
                var a = Bb(".bodymask");
                a.length ? a.show() : (a = Bb("<div>"), Bb(function() {
                    var b = Bb("body");
                    b.addClass("bodymaskopen"),
                    a.appendTo(b).focus(),
                    setTimeout(function() {
                        try {
                            a.addClass("bodymask")
                        } catch(b) {}
                    },
                    0)
                }))
            }
        },
        hideLoading: function(a) {
            if (this.BAR_IS_LOADED) {
                var b = Bb("body"),
                c = Bb(".bodymask");
                c.length && (c.blur(), c.hide(), a || c.remove()),
                b.removeClass("bodymaskopen"),
                this.BAR_IS_LOADED = !1
            }
        }
    });
    var Bd = 0,
    Cd = !1,
    Dd = W(), Ed = !0, Fd = {},
    Gd = {},
    Hd = {
        moment: {
            name: "moment",
            js: [Dd + "/js/lib/moment.js"]
        },
        jquery: {
            name: "jquery",
            js: [{
                src: Dd + "/js/lib/jquery/jquery-1.11.1.js"
            }]
        },
        cookie: {
            name: "cookie",
            js: [Dd + "/js/lib/jquery/jquery.cookie.min.js"],
            deps: ["jquery"]
        },
        sparkline: {
            name: "sparkline",
            js: [Dd + "/js/lib/jquery/jquery.sparkline.min.js"],
            deps: ["jquery"]
        },
        fullcalendar: {
            name: "fullcalendar",
            css: [Dd + "/js/lib/fullcalendar/css/fullcalendar.css"],
            js: [Dd + "/js/lib/fullcalendar/fullcalendar.min.js", Dd + "/js/lib/fullcalendar/lang/zh-cn.js"],
            deps: ["jquery", "moment"]
        },
        bootstrap: {
            name: "bootstrap",
            css: [Dd + "/js/lib/bootstrap/bootstrap.min.css"],
            js: [Dd + "/js/lib/bootstrap/bootstrap.js"],
            deps: ["jquery"]
        },
        fusioncharts: {
            name: "fusioncharts",
            js: [Dd + "/js/lib/fusioncharts/fusioncharts.js"],
            callback: function() {
                Bb.FChart = function(a, b, c, d, e, f, g, h) {
                    var i = e ? e: "json",
                    j = f ? f: "100%",
                    k = g ? g: "100%",
                    l = /[a-zA-Z0-9]+/.test(b) ? b: "column2d",
                    m = {
                        type: l,
                        dataSource: d,
                        dataFormat: i,
                        width: j,
                        height: k,
                        id: a.toString()
                    };
                    h && Bb.extend(m, h);
                    var n = new FusionCharts(m);
                    return n.render(c),
                    n
                }
            }
        },
        echarts: {
            name: "echarts",
            js: [Dd + "/js/lib/echarts/echarts.min.js"],
            callback: function() {
                Bb.EChart = function(a, b, c, d) {
                    var e = c ? c: "100%",
                    f = d ? d: "100%",
                    g = 320,
                    h = Bb("#" + a);
                    h.css(/%$/.test("" + f) ? {
                        width: e,
                        height: parseInt(f) * (h.height() < 200 ? g: h.height()) / 100
                    }: {
                        width: e,
                        height: f
                    });
                    var i = echarts.init(h[0]);
                    return i.setOption(b),
                    i
                }
            }
        }
    },
    Id = a, Jd = !1, Kd = [], Ld = Lb, Md = Ld.head || Ld.getElementsByTagName("head")[0] || Ld.documentElement, Nd = Md.getElementsByTagName("base")[0]; jb(), Bb.WEBROOT = Dd, Bb.JSROOT = Dd + "/js", Bb.JSLIB = Dd + "/js/lib", Bb.require = function(b, c) {
        Cd || fb("jQuery.require Uninitialized");
        var d = Bb.Deferred();
        Kd.push(d);
        var e = Z(b);
        return Bb.each(e,
        function(a, b) {
            Kd.push(b)
        }),
        Bb.showLoading(),
        gb(),
        d.promise().done(function() {
            c && c.apply(a),
            Bb.hideLoading()
        })
    },
    Bb.fn.extend({
        loopBlock: kb,
        fillBlock: lb
    }), Bb.extend({
        dto2obj: mb,
        obj2dto: nb,
        obj2arr: ob
    });
    var Od = {}; Bb.ajaxPrefilter(function(a, b, c) {
        if (a.showHint && (a.error = !0 === a.showHint ? pb: Bb.isFunction(a.showHint) ? a.showHint: Bb.noop), a.banEcho) {
            var d, e = parseInt(a.banEchoSecond || 2e3);
            e = !isNaN(e) && e > 0 ? e: 0,
            Od && a.url == Od.url && a.data === Od.data || d ? (alert("请求正在执行，请稍后..."), c.abort()) : (Od = {
                url: a.url,
                data: a.data
            },
            e && (d = setTimeout(function() {
                Od = {},
                d && (clearTimeout(d), d = null)
            },
            e)), c.always(function() {
                Od = {},
                d && (clearTimeout(d), d = null)
            }))
        }
    }), Bb.toJSON = "object" == typeof JSON && JSON.stringify ? JSON.stringify: function(a) {
        var b = Object.prototype.hasOwnProperty;
        if (null === a) return "null";
        var c, d, e, f, g = Bb.type(a);
        if ("undefined" === g) return void 0;
        if ("number" === g || "boolean" === g) return String(a);
        if ("string" === g) return qb(a);
        if ("function" == typeof a.toJSON) return Bb.toJSON(a.toJSON());
        if ("date" === g) {
            var h = a.getUTCMonth() + 1,
            i = a.getUTCDate(),
            j = a.getUTCFullYear(),
            k = a.getUTCHours(),
            l = a.getUTCMinutes(),
            m = a.getUTCSeconds(),
            n = a.getUTCMilliseconds();
            return 10 > h && (h = "0" + h),
            10 > i && (i = "0" + i),
            10 > k && (k = "0" + k),
            10 > l && (l = "0" + l),
            10 > m && (m = "0" + m),
            100 > n && (n = "0" + n),
            10 > n && (n = "0" + n),
            '"' + j + "-" + h + "-" + i + "T" + k + ":" + l + ":" + m + "." + n + 'Z"'
        }
        if (c = [], Bb.isArray(a)) {
            for (d = 0; d < a.length; d++) c.push(Bb.toJSON(a[d]) || "null");
            return "[" + c.join(",") + "]"
        }
        if ("object" == typeof a) {
            for (d in a) if (b.call(a, d)) {
                if (g = typeof d, "number" === g) e = '"' + d + '"';
                else {
                    if ("string" !== g) continue;
                    e = qb(d)
                }
                g = typeof a[d],
                "function" !== g && "undefined" !== g && (f = Bb.toJSON(a[d]), c.push(e + ":" + f))
            }
            return "{" + c.join(",") + "}"
        }
    },
    Bb.extend({
        isUrl: function(a, b) {
            var c = b instanceof RegExp ? b: /^([a-zA-Z]+):\/\/(([0-9a-zA-Z.]+)(:([0-9]+))?)((\/[\w-]+(?=\/))*\/((.+)(?=\?))?(\?(.+))?)?$/;
            return !! a && !/[<>"]/.test(a) && c.test(a)
        },
        isEmail: function(a, b) {
            var c = b instanceof RegExp ? b: /^([a-zA-Z]\w*)@([\w-]+)((\.[a-zA-Z]{2,5}){1,2})$/;
            return !! a && c.test(a)
        },
        inRng: function(a, b, c) {
            var d = parseFloat(b),
            e = parseFloat(c),
            f = !1,
            g = Bb.isNumeric(a) ? parseFloat(a) : a.length;
            return isNaN(d) || (f = g - d >= 0),
            isNaN(e) || (f = isNaN(d) ? 0 >= g - e: f && 0 >= g - e),
            f
        },
        isTelephone: function(a, b) {
            var c = b instanceof RegExp ? b: /(^[0-9]{7,8}$)|(^0[0-9]{1,3}-[0-9]{7,8}$)|(^0[0-9]{1,3}-[0-9]{7,8}-[0-9]{1,4}$)/,
            d = c.test(a);
            if (!d) {
                var e = "00852",
                f = "00853",
                g = "00886"; (a.startWith(e) || a.startWith(f) || a.startWith(g)) && (d = /(^[0-9]{5}-[0-9]{7,8}$)|(^[0-9]{5}-[0-9]{7,8}-[0-9]{1,4}$)/.test(a))
            }
            return d
        },
        isMobilephone: function(a, b) {
            var c = b instanceof RegExp ? b: /^1[3-8]\d{9}$/;
            return !! a && c.test(a)
        }
    }), Date.prototype.format = function(a, b) {
        if ("Invalid Date" == this) return this;
        a = void 0 === a || null === a || "" === a.replace(/^\s|\s$/g, "") ? "Ymd": a.replace(/^\s|\s$/g, "");
        var c = this,
        d = function(a, b) {
            b || (b = 2),
            a = String(a);
            for (var c = 0,
            d = ""; c < b - a.length; c++) d += "0";
            return d + a
        },
        e = {
            en: {
                n: ["am", "pm"],
                N: ["AM", "PM"],
                w: ["Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat"],
                W: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
                m: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                M: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
            },
            zh: {
                n: ["上午", "下午"],
                N: ["上午", "下午"],
                w: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
                W: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
                m: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                M: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
            }
        };
        return b = e["" + b] ? b: "en",
        a.replace(/[a-z]/gi,
        function(a) {
            switch (a) {
            case "a":
                return c.getHours() < 12 ? e[b].n[0] : e[b].n[1];
            case "A":
                return c.getHours() < 12 ? e[b].N[0] : e[b].N[1];
            case "d":
                return d(c.getDate());
            case "D":
                return e[b].w[c.getDay()];
            case "f":
                return c.getMilliseconds();
            case "F":
                return e[b].M[c.getMonth()];
            case "h":
                return d(c.getHours() % 12 || 12);
            case "H":
                return d(c.getHours());
            case "g":
                return c.getHours() % 12 || 12;
            case "G":
                return c.getHours();
            case "i":
                return d(c.getMinutes());
            case "j":
                return c.getDate();
            case "l":
                return e[b].W[c.getDay()];
            case "m":
                return d(c.getMonth() + 1);
            case "M":
                return e[b].m[c.getMonth()];
            case "n":
                return c.getMonth() + 1;
            case "s":
                return d(c.getSeconds());
            case "U":
                return c.getTime();
            case "w":
                return c.getDay();
            case "y":
                return String(c.getFullYear()).substr(2);
            case "Y":
                return c.getFullYear();
            case "z":
                var f = 864e5,
                g = new Date(c.getFullYear() + "/1/1 00:00:00"),
                h = c.getTime() * g > 0 ? c.getTime() - g: c.getTime() + g;
                return h = Math.abs(h),
                Math.ceil(h / f);
            case "Z":
                var f = 6048e5,
                g = new Date(c.getFullYear() + "/1/1 00:00:00"),
                h = c.getTime() * g > 0 ? c.getTime() - g: c.getTime() + g;
                return h = Math.abs(h),
                Math.ceil(h / f);
            default:
                return a
            }
        })
    },
    Bb.extend({
        getDate: function(a, b, c) {
            return "Invalid Date" == new Date(a.replace(/-/g, "/")) ? (c = b, b = a, a = new Date) : a = new Date(a.replace(/-/g, "/")),
            a.format(b ? b: "Y-m-d", c ? c: "en")
        }
    }), "function" == typeof define && define.amd && define("jquery", [],
    function() {
        return Bb
    });
    var Pd = a.jQuery,
    Qd = a.$;
    return Bb.noConflict = function(b) {
        return a.$ === Bb && (a.$ = Qd),
        b && a.jQuery === Bb && (a.jQuery = Pd),
        Bb
    },
    typeof b === Ub && (a.jQuery = a.$ = Bb), Bb
}),
!
function(a) {
    "use strict";
    a.extend({
        tmpl: function(a, b) {
            return ("" + a).replace(/\{\{(\d+)\}\}/g,
            function(a, c) {
                var d = c < b.length ? b[c] : "";
                return d
            })
        }
    })
} (jQuery),
"undefined" == typeof jQuery) throw new Error("Bootstrap's JavaScript requires jQuery"); +
function(a) {
    var b = a.fn.jquery.split(" ")[0].split(".");
    if (b[0] < 2 && b[1] < 9 || 1 == b[0] && 9 == b[1] && b[2] < 1) throw new Error("Bootstrap's JavaScript requires jQuery version 1.9.1 or higher")
} (jQuery), +
function(a) {
    "use strict";
    function b() {
        var a = document.createElement("bootstrap"),
        b = {
            WebkitTransition: "webkitTransitionEnd",
            MozTransition: "transitionend",
            OTransition: "oTransitionEnd otransitionend",
            transition: "transitionend"
        };
        for (var c in b) if (void 0 !== a.style[c]) return {
            end: b[c]
        };
        return ! 1
    }
    a.fn.emulateTransitionEnd = function(b) {
        var c = !1,
        d = this;
        a(this).one("bsTransitionEnd",
        function() {
            c = !0
        });
        var e = function() {
            c || a(d).trigger(a.support.transition.end)
        };
        return setTimeout(e, b),
        this
    },
    a(function() {
        a.support.transition = b(),
        a.support.transition && (a.event.special.bsTransitionEnd = {
            bindType: a.support.transition.end,
            delegateType: a.support.transition.end,
            handle: function(b) {
                return a(b.target).is(this) ? b.handleObj.handler.apply(this, arguments) : void 0
            }
        })
    })
} (jQuery), +
function(a) {
    "use strict";
    function b(b) {
        return this.each(function() {
            var c = a(this),
            e = c.data("bs.alert");
            e || c.data("bs.alert", e = new d(this)),
            "string" == typeof b && e[b].call(c)
        })
    }
    var c = '[data-dismiss="alert"]',
    d = function(b) {
        a(b).on("click", c, this.close)
    };
    d.VERSION = "3.3.1",
    d.TRANSITION_DURATION = 150,
    d.prototype.close = function(b) {
        function c() {
            g.detach().trigger("closed.bs.alert").remove()
        }
        var e = a(this),
        f = e.attr("data-target");
        f || (f = e.attr("href"), f = f && f.replace(/.*(?=#[^\s]*$)/, ""));
        var g = a(f);
        b && b.preventDefault(),
        g.length || (g = e.closest(".alert")),
        g.trigger(b = a.Event("close.bs.alert")),
        b.isDefaultPrevented() || (g.removeClass("in"), a.support.transition && g.hasClass("fade") ? g.one("bsTransitionEnd", c).emulateTransitionEnd(d.TRANSITION_DURATION) : c())
    };
    var e = a.fn.alert;
    a.fn.alert = b,
    a.fn.alert.Constructor = d,
    a.fn.alert.noConflict = function() {
        return a.fn.alert = e,
        this
    },
    a(document).on("click.bs.alert.data-api", c, d.prototype.close)
} (jQuery), +
function(a) {
    "use strict";
    function b(b) {
        return this.each(function() {
            var d = a(this),
            e = d.data("bs.button"),
            f = "object" == typeof b && b;
            e || d.data("bs.button", e = new c(this, f)),
            "toggle" == b ? e.toggle() : b && e.setState(b)
        })
    }
    var c = function(b, d) {
        this.$element = a(b),
        this.options = a.extend({},
        c.DEFAULTS, d),
        this.isLoading = !1
    };
    c.VERSION = "3.3.1",
    c.DEFAULTS = {
        loadingText: "loading..."
    },
    c.prototype.setState = function(b) {
        var c = "disabled",
        d = this.$element,
        e = d.is("input") ? "val": "html",
        f = d.data();
        b += "Text",
        null == f.resetText && d.data("resetText", d[e]()),
        setTimeout(a.proxy(function() {
            d[e](null == f[b] ? this.options[b] : f[b]),
            "loadingText" == b ? (this.isLoading = !0, d.addClass(c).attr(c, c)) : this.isLoading && (this.isLoading = !1, d.removeClass(c).removeAttr(c))
        },
        this), 0)
    },
    c.prototype.toggle = function() {
        var a = !0,
        b = this.$element.closest('[data-toggle="buttons"]');
        if (b.length) {
            var c = this.$element.find("input");
            "radio" == c.prop("type") && (c.prop("checked") && this.$element.hasClass("active") ? a = !1 : b.find(".active").removeClass("active")),
            a && c.prop("checked", !this.$element.hasClass("active")).trigger("change")
        } else this.$element.attr("aria-pressed", !this.$element.hasClass("active"));
        a && this.$element.toggleClass("active")
    };
    var d = a.fn.button;
    a.fn.button = b,
    a.fn.button.Constructor = c,
    a.fn.button.noConflict = function() {
        return a.fn.button = d,
        this
    },
    a(document).on("click.bs.button.data-api", '[data-toggle^="button"]',
    function(c) {
        var d = a(c.target);
        d.hasClass("btn") || (d = d.closest(".btn")),
        b.call(d, "toggle"),
        c.preventDefault()
    }).on("focus.bs.button.data-api blur.bs.button.data-api", '[data-toggle^="button"]',
    function(b) {
        a(b.target).closest(".btn").toggleClass("focus", /^focus(in)?$/.test(b.type))
    })
} (jQuery), +
function(a) {
    "use strict";
    function b(b) {
        return this.each(function() {
            var d = a(this),
            e = d.data("bs.carousel"),
            f = a.extend({},
            c.DEFAULTS, d.data(), "object" == typeof b && b),
            g = "string" == typeof b ? b: f.slide;
            e || d.data("bs.carousel", e = new c(this, f)),
            "number" == typeof b ? e.to(b) : g ? e[g]() : f.interval && e.pause().cycle()
        })
    }
    var c = function(b, c) {
        this.$element = a(b),
        this.$indicators = this.$element.find(".carousel-indicators"),
        this.options = c,
        this.paused = this.sliding = this.interval = this.$active = this.$items = null,
        this.options.keyboard && this.$element.on("keydown.bs.carousel", a.proxy(this.keydown, this)),
        "hover" == this.options.pause && !("ontouchstart" in document.documentElement) && this.$element.on("mouseenter.bs.carousel", a.proxy(this.pause, this)).on("mouseleave.bs.carousel", a.proxy(this.cycle, this))
    };
    c.VERSION = "3.3.1",
    c.TRANSITION_DURATION = 600,
    c.DEFAULTS = {
        interval: 5e3,
        pause: "hover",
        wrap: !0,
        keyboard: !0
    },
    c.prototype.keydown = function(a) {
        if (!/input|textarea/i.test(a.target.tagName)) {
            switch (a.which) {
            case 37:
                this.prev();
                break;
            case 39:
                this.next();
                break;
            default:
                return
            }
            a.preventDefault()
        }
    },
    c.prototype.cycle = function(b) {
        return b || (this.paused = !1),
        this.interval && clearInterval(this.interval),
        this.options.interval && !this.paused && (this.interval = setInterval(a.proxy(this.next, this), this.options.interval)),
        this
    },
    c.prototype.getItemIndex = function(a) {
        return this.$items = a.parent().children(".item"),
        this.$items.index(a || this.$active)
    },
    c.prototype.getItemForDirection = function(a, b) {
        var c = "prev" == a ? -1 : 1,
        d = this.getItemIndex(b),
        e = (d + c) % this.$items.length;
        return this.$items.eq(e)
    },
    c.prototype.to = function(a) {
        var b = this,
        c = this.getItemIndex(this.$active = this.$element.find(".item.active"));
        return a > this.$items.length - 1 || 0 > a ? void 0 : this.sliding ? this.$element.one("slid.bs.carousel",
        function() {
            b.to(a)
        }) : c == a ? this.pause().cycle() : this.slide(a > c ? "next": "prev", this.$items.eq(a))
    },
    c.prototype.pause = function(b) {
        return b || (this.paused = !0),
        this.$element.find(".next, .prev").length && a.support.transition && (this.$element.trigger(a.support.transition.end), this.cycle(!0)),
        this.interval = clearInterval(this.interval),
        this
    },
    c.prototype.next = function() {
        return this.sliding ? void 0 : this.slide("next")
    },
    c.prototype.prev = function() {
        return this.sliding ? void 0 : this.slide("prev")
    },
    c.prototype.slide = function(b, d) {
        var e = this.$element.find(".item.active"),
        f = d || this.getItemForDirection(b, e),
        g = this.interval,
        h = "next" == b ? "left": "right",
        i = "next" == b ? "first": "last",
        j = this;
        if (!f.length) {
            if (!this.options.wrap) return;
            f = this.$element.find(".item")[i]()
        }
        if (f.hasClass("active")) return this.sliding = !1;
        var k = f[0],
        l = a.Event("slide.bs.carousel", {
            relatedTarget: k,
            direction: h
        });
        if (this.$element.trigger(l), !l.isDefaultPrevented()) {
            if (this.sliding = !0, g && this.pause(), this.$indicators.length) {
                this.$indicators.find(".active").removeClass("active");
                var m = a(this.$indicators.children()[this.getItemIndex(f)]);
                m && m.addClass("active")
            }
            var n = a.Event("slid.bs.carousel", {
                relatedTarget: k,
                direction: h
            });
            return a.support.transition && this.$element.hasClass("slide") ? (f.addClass(b), f[0].offsetWidth, e.addClass(h), f.addClass(h), e.one("bsTransitionEnd",
            function() {
                f.removeClass([b, h].join(" ")).addClass("active"),
                e.removeClass(["active", h].join(" ")),
                j.sliding = !1,
                setTimeout(function() {
                    j.$element.trigger(n)
                },
                0)
            }).emulateTransitionEnd(c.TRANSITION_DURATION)) : (e.removeClass("active"), f.addClass("active"), this.sliding = !1, this.$element.trigger(n)),
            g && this.cycle(),
            this
        }
    };
    var d = a.fn.carousel;
    a.fn.carousel = b,
    a.fn.carousel.Constructor = c,
    a.fn.carousel.noConflict = function() {
        return a.fn.carousel = d,
        this
    };
    var e = function(c) {
        var d, e = a(this),
        f = a(e.attr("data-target") || (d = e.attr("href")) && d.replace(/.*(?=#[^\s]+$)/, ""));
        if (f.hasClass("carousel")) {
            var g = a.extend({},
            f.data(), e.data()),
            h = e.attr("data-slide-to");
            h && (g.interval = !1),
            b.call(f, g),
            h && f.data("bs.carousel").to(h),
            c.preventDefault()
        }
    };
    a(document).on("click.bs.carousel.data-api", "[data-slide]", e).on("click.bs.carousel.data-api", "[data-slide-to]", e),
    a(window).on("load",
    function() {
        a('[data-ride="carousel"]').each(function() {
            var c = a(this);
            b.call(c, c.data())
        })
    })
} (jQuery), +
function(a) {
    "use strict";
    function b(b) {
        var c, d = b.attr("data-target") || (c = b.attr("href")) && c.replace(/.*(?=#[^\s]+$)/, "");
        return a(d)
    }
    function c(b) {
        return this.each(function() {
            var c = a(this),
            e = c.data("bs.collapse"),
            f = a.extend({},
            d.DEFAULTS, c.data(), "object" == typeof b && b); ! e && f.toggle && "show" == b && (f.toggle = !1),
            e || c.data("bs.collapse", e = new d(this, f)),
            "string" == typeof b && e[b]()
        })
    }
    var d = function(b, c) {
        this.$element = a(b),
        this.options = a.extend({},
        d.DEFAULTS, c),
        this.$trigger = a(this.options.trigger).filter('[href="#' + b.id + '"], [data-target="#' + b.id + '"]'),
        this.transitioning = null,
        this.options.parent ? this.$parent = this.getParent() : this.addAriaAndCollapsedClass(this.$element, this.$trigger),
        this.options.toggle && this.toggle()
    };
    d.VERSION = "3.3.1",
    d.TRANSITION_DURATION = 350,
    d.DEFAULTS = {
        toggle: !0,
        trigger: '[data-toggle="collapse"]'
    },
    d.prototype.dimension = function() {
        var a = this.$element.hasClass("width");
        return a ? "width": "height"
    },
    d.prototype.show = function() {
        if (!this.transitioning && !this.$element.hasClass("in")) {
            var b, e = this.$parent && this.$parent.find("> .panel").children(".in, .collapsing");
            if (! (e && e.length && (b = e.data("bs.collapse"), b && b.transitioning))) {
                var f = a.Event("show.bs.collapse");
                if (this.$element.trigger(f), !f.isDefaultPrevented()) {
                    e && e.length && (c.call(e, "hide"), b || e.data("bs.collapse", null));
                    var g = this.dimension();
                    this.$element.removeClass("collapse").addClass("collapsing")[g](0).attr("aria-expanded", !0),
                    this.$trigger.removeClass("collapsed").attr("aria-expanded", !0),
                    this.transitioning = 1;
                    var h = function() {
                        this.$element.removeClass("collapsing").addClass("collapse in")[g](""),
                        this.transitioning = 0,
                        this.$element.trigger("shown.bs.collapse")
                    };
                    if (!a.support.transition) return h.call(this);
                    var i = a.camelCase(["scroll", g].join("-"));
                    this.$element.one("bsTransitionEnd", a.proxy(h, this)).emulateTransitionEnd(d.TRANSITION_DURATION)[g](this.$element[0][i])
                }
            }
        }
    },
    d.prototype.hide = function() {
        if (!this.transitioning && this.$element.hasClass("in")) {
            var b = a.Event("hide.bs.collapse");
            if (this.$element.trigger(b), !b.isDefaultPrevented()) {
                var c = this.dimension();
                this.$element[c](this.$element[c]())[0].offsetHeight,
                this.$element.addClass("collapsing").removeClass("collapse in").attr("aria-expanded", !1),
                this.$trigger.addClass("collapsed").attr("aria-expanded", !1),
                this.transitioning = 1;
                var e = function() {
                    this.transitioning = 0,
                    this.$element.removeClass("collapsing").addClass("collapse").trigger("hidden.bs.collapse")
                };
                return a.support.transition ? void this.$element[c](0).one("bsTransitionEnd", a.proxy(e, this)).emulateTransitionEnd(d.TRANSITION_DURATION) : e.call(this)
            }
        }
    },
    d.prototype.toggle = function() {
        this[this.$element.hasClass("in") ? "hide": "show"]()
    },
    d.prototype.getParent = function() {
        return a(this.options.parent).find('[data-toggle="collapse"][data-parent="' + this.options.parent + '"]').each(a.proxy(function(c, d) {
            var e = a(d);
            this.addAriaAndCollapsedClass(b(e), e)
        },
        this)).end()
    },
    d.prototype.addAriaAndCollapsedClass = function(a, b) {
        var c = a.hasClass("in");
        a.attr("aria-expanded", c),
        b.toggleClass("collapsed", !c).attr("aria-expanded", c)
    };
    var e = a.fn.collapse;
    a.fn.collapse = c,
    a.fn.collapse.Constructor = d,
    a.fn.collapse.noConflict = function() {
        return a.fn.collapse = e,
        this
    },
    a(document).on("click.bs.collapse.data-api", '[data-toggle="collapse"]',
    function(d) {
        var e = a(this);
        e.attr("data-target") || d.preventDefault();
        var f = b(e),
        g = f.data("bs.collapse"),
        h = g ? "toggle": a.extend({},
        e.data(), {
            trigger: this
        });
        c.call(f, h)
    })
} (jQuery), +
function(a) {
    "use strict";
    function b(b) {
        b && 3 === b.which || (a(e).remove(), a(f).each(function() {
            var d = a(this),
            e = c(d),
            f = {
                relatedTarget: this
            };
            e.hasClass("open") && (e.trigger(b = a.Event("hide.bs.dropdown", f)), b.isDefaultPrevented() || (d.attr("aria-expanded", "false"), e.removeClass("open").trigger("hidden.bs.dropdown", f)))
        }))
    }
    function c(b) {
        var c = b.attr("data-target");
        c || (c = b.attr("href"), c = c && /#[A-Za-z]/.test(c) && c.replace(/.*(?=#[^\s]*$)/, ""));
        var d = c && a(c);
        return d && d.length ? d: b.parent()
    }
    function d(b) {
        return this.each(function() {
            var c = a(this),
            d = c.data("bs.dropdown");
            d || c.data("bs.dropdown", d = new g(this)),
            "string" == typeof b && d[b].call(c)
        })
    }
    var e = ".dropdown-backdrop",
    f = '[data-toggle="dropdown"]',
    g = function(b) {
        a(b).on("click.bs.dropdown", this.toggle)
    };
    g.VERSION = "3.3.1",
    g.prototype.toggle = function(d) {
        var e = a(this);
        if (!e.is(".disabled, :disabled")) {
            var f = c(e),
            g = f.hasClass("open");
            if (b(), !g) {
                "ontouchstart" in document.documentElement && !f.closest(".navbar-nav").length && a('<div class="dropdown-backdrop"/>').insertAfter(a(this)).on("click", b);
                var h = {
                    relatedTarget: this
                };
                if (f.trigger(d = a.Event("show.bs.dropdown", h)), d.isDefaultPrevented()) return;
                e.trigger("focus").attr("aria-expanded", "true"),
                f.toggleClass("open").trigger("shown.bs.dropdown", h)
            }
            return ! 1
        }
    },
    g.prototype.keydown = function(b) {
        if (/(38|40|27|32)/.test(b.which) && !/input|textarea/i.test(b.target.tagName)) {
            var d = a(this);
            if (b.preventDefault(), b.stopPropagation(), !d.is(".disabled, :disabled")) {
                var e = c(d),
                g = e.hasClass("open");
                if (!g && 27 != b.which || g && 27 == b.which) return 27 == b.which && e.find(f).trigger("focus"),
                d.trigger("click");
                var h = " li:not(.divider):visible a",
                i = e.find('[role="menu"]' + h + ', [role="listbox"]' + h);
                if (i.length) {
                    var j = i.index(b.target);
                    38 == b.which && j > 0 && j--,
                    40 == b.which && j < i.length - 1 && j++,
                    ~j || (j = 0),
                    i.eq(j).trigger("focus")
                }
            }
        }
    };
    var h = a.fn.dropdown;
    a.fn.dropdown = d,
    a.fn.dropdown.Constructor = g,
    a.fn.dropdown.noConflict = function() {
        return a.fn.dropdown = h,
        this
    },
    a(document).on("click.bs.dropdown.data-api", b).on("click.bs.dropdown.data-api", ".dropdown form",
    function(a) {
        a.stopPropagation()
    }).on("click.bs.dropdown.data-api", f, g.prototype.toggle).on("keydown.bs.dropdown.data-api", f, g.prototype.keydown).on("keydown.bs.dropdown.data-api", '[role="menu"]', g.prototype.keydown).on("keydown.bs.dropdown.data-api", '[role="listbox"]', g.prototype.keydown)
} (jQuery), +
function(a) {
    "use strict";
    function b(b, d) {
        return this.each(function() {
            var e = a(this),
            f = e.data("bs.modal"),
            g = a.extend({},
            c.DEFAULTS, e.data(), "object" == typeof b && b);
            f || e.data("bs.modal", f = new c(this, g)),
            "string" == typeof b ? f[b](d) : g.show && f.show(d)
        })
    }
    var c = function(b, c) {
        this.options = c,
        this.$body = a(document.body),
        this.$element = a(b),
        this.$backdrop = this.isShown = null,
        this.scrollbarWidth = 0,
        this.options.remote && this.$element.find(".modal-content").load(this.options.remote, a.proxy(function() {
            this.$element.trigger("loaded.bs.modal")
        },
        this))
    };
    c.VERSION = "3.3.1",
    c.TRANSITION_DURATION = 300,
    c.BACKDROP_TRANSITION_DURATION = 150,
    c.DEFAULTS = {
        backdrop: !0,
        keyboard: !0,
        show: !0
    },
    c.prototype.toggle = function(a) {
        return this.isShown ? this.hide() : this.show(a)
    },
    c.prototype.show = function(b) {
        var d = this,
        e = a.Event("show.bs.modal", {
            relatedTarget: b
        });
        this.$element.trigger(e),
        this.isShown || e.isDefaultPrevented() || (this.isShown = !0, this.checkScrollbar(), this.setScrollbar(), this.$body.addClass("modal-open"), this.escape(), this.resize(), this.$element.on("click.dismiss.bs.modal", '[data-dismiss="modal"]', a.proxy(this.hide, this)), this.backdrop(function() {
            var e = a.support.transition && d.$element.hasClass("fade");
            d.$element.parent().length || d.$element.appendTo(d.$body),
            d.$element.show().scrollTop(0),
            d.options.backdrop && d.adjustBackdrop(),
            d.adjustDialog(),
            e && d.$element[0].offsetWidth,
            d.$element.addClass("in").attr("aria-hidden", !1),
            d.enforceFocus();
            var f = a.Event("shown.bs.modal", {
                relatedTarget: b
            });
            e ? d.$element.find(".modal-dialog").one("bsTransitionEnd",
            function() {
                d.$element.trigger("focus").trigger(f)
            }).emulateTransitionEnd(c.TRANSITION_DURATION) : d.$element.trigger("focus").trigger(f)
        }))
    },
    c.prototype.hide = function(b) {
        b && b.preventDefault(),
        b = a.Event("hide.bs.modal"),
        this.$element.trigger(b),
        this.isShown && !b.isDefaultPrevented() && (this.isShown = !1, this.escape(), this.resize(), a(document).off("focusin.bs.modal"), this.$element.removeClass("in").attr("aria-hidden", !0).off("click.dismiss.bs.modal"), a.support.transition && this.$element.hasClass("fade") ? this.$element.one("bsTransitionEnd", a.proxy(this.hideModal, this)).emulateTransitionEnd(c.TRANSITION_DURATION) : this.hideModal())
    },
    c.prototype.enforceFocus = function() {
        a(document).off("focusin.bs.modal").on("focusin.bs.modal", a.proxy(function(a) {
            this.$element[0] === a.target || this.$element.has(a.target).length || this.$element.trigger("focus")
        },
        this))
    },
    c.prototype.escape = function() {
        this.isShown && this.options.keyboard ? this.$element.on("keydown.dismiss.bs.modal", a.proxy(function(a) {
            27 == a.which && this.hide()
        },
        this)) : this.isShown || this.$element.off("keydown.dismiss.bs.modal")
    },
    c.prototype.resize = function() {
        this.isShown ? a(window).on("resize.bs.modal", a.proxy(this.handleUpdate, this)) : a(window).off("resize.bs.modal")
    },
    c.prototype.hideModal = function() {
        var a = this;
        this.$element.hide(),
        this.backdrop(function() {
            a.$body.removeClass("modal-open"),
            a.resetAdjustments(),
            a.resetScrollbar(),
            a.$element.trigger("hidden.bs.modal")
        })
    },
    c.prototype.removeBackdrop = function() {
        this.$backdrop && this.$backdrop.remove(),
        this.$backdrop = null
    },
    c.prototype.backdrop = function(b) {
        var d = this,
        e = this.$element.hasClass("fade") ? "fade": "";
        if (this.isShown && this.options.backdrop) {
            var f = a.support.transition && e;
            if (this.$backdrop = a('<div class="modal-backdrop ' + e + '" />').prependTo(this.$element).on("click.dismiss.bs.modal", a.proxy(function(a) {
                a.target === a.currentTarget && ("static" == this.options.backdrop ? this.$element[0].focus.call(this.$element[0]) : this.hide.call(this))
            },
            this)), f && this.$backdrop[0].offsetWidth, this.$backdrop.addClass("in"), !b) return;
            f ? this.$backdrop.one("bsTransitionEnd", b).emulateTransitionEnd(c.BACKDROP_TRANSITION_DURATION) : b()
        } else if (!this.isShown && this.$backdrop) {
            this.$backdrop.removeClass("in");
            var g = function() {
                d.removeBackdrop(),
                b && b()
            };
            a.support.transition && this.$element.hasClass("fade") ? this.$backdrop.one("bsTransitionEnd", g).emulateTransitionEnd(c.BACKDROP_TRANSITION_DURATION) : g()
        } else b && b()
    },
    c.prototype.handleUpdate = function() {
        this.options.backdrop && this.adjustBackdrop(),
        this.adjustDialog()
    },
    c.prototype.adjustBackdrop = function() {
        this.$backdrop.css("height", 0).css("height", this.$element[0].scrollHeight)
    },
    c.prototype.adjustDialog = function() {
        var a = this.$element[0].scrollHeight > document.documentElement.clientHeight;
        this.$element.css({
            paddingLeft: !this.bodyIsOverflowing && a ? this.scrollbarWidth: "",
            paddingRight: this.bodyIsOverflowing && !a ? this.scrollbarWidth: ""
        })
    },
    c.prototype.resetAdjustments = function() {
        this.$element.css({
            paddingLeft: "",
            paddingRight: ""
        })
    },
    c.prototype.checkScrollbar = function() {
        this.bodyIsOverflowing = document.body.scrollHeight > document.documentElement.clientHeight,
        this.scrollbarWidth = this.measureScrollbar()
    },
    c.prototype.setScrollbar = function() {
        var a = parseInt(this.$body.css("padding-right") || 0, 10);
        this.bodyIsOverflowing && this.$body.css("padding-right", a + this.scrollbarWidth)
    },
    c.prototype.resetScrollbar = function() {
        this.$body.css("padding-right", "")
    },
    c.prototype.measureScrollbar = function() {
        var a = document.createElement("div");
        a.className = "modal-scrollbar-measure",
        this.$body.append(a);
        var b = a.offsetWidth - a.clientWidth;
        return this.$body[0].removeChild(a),
        b
    };
    var d = a.fn.modal;
    a.fn.modal = b,
    a.fn.modal.Constructor = c,
    a.fn.modal.noConflict = function() {
        return a.fn.modal = d,
        this
    },
    a(document).on("click.bs.modal.data-api", '[data-toggle="modal"]',
    function(c) {
        var d = a(this),
        e = d.attr("href"),
        f = a(d.attr("data-target") || e && e.replace(/.*(?=#[^\s]+$)/, "")),
        g = f.data("bs.modal") ? "toggle": a.extend({
            remote: !/#/.test(e) && e
        },
        f.data(), d.data());
        d.is("a") && c.preventDefault(),
        f.one("show.bs.modal",
        function(a) {
            a.isDefaultPrevented() || f.one("hidden.bs.modal",
            function() {
                d.is(":visible") && d.trigger("focus")
            })
        }),
        b.call(f, g, this)
    })
} (jQuery), +
function(a) {
    "use strict";
    function b(b) {
        return this.each(function() {
            var d = a(this),
            e = d.data("bs.tooltip"),
            f = "object" == typeof b && b,
            g = f && f.selector; (e || "destroy" != b) && (g ? (e || d.data("bs.tooltip", e = {}), e[g] || (e[g] = new c(this, f))) : e || d.data("bs.tooltip", e = new c(this, f)), "string" == typeof b && e[b]())
        })
    }
    var c = function(a, b) {
        this.type = this.options = this.enabled = this.timeout = this.hoverState = this.$element = null,
        this.init("tooltip", a, b)
    };
    c.VERSION = "3.3.1",
    c.TRANSITION_DURATION = 150,
    c.DEFAULTS = {
        animation: !0,
        placement: "top",
        selector: !1,
        template: '<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>',
        trigger: "hover focus",
        title: "",
        delay: 0,
        html: !1,
        container: !1,
        viewport: {
            selector: "body",
            padding: 0
        }
    },
    c.prototype.init = function(b, c, d) {
        this.enabled = !0,
        this.type = b,
        this.$element = a(c),
        this.options = this.getOptions(d),
        this.$viewport = this.options.viewport && a(this.options.viewport.selector || this.options.viewport);
        for (var e = this.options.trigger.split(" "), f = e.length; f--;) {
            var g = e[f];
            if ("click" == g) this.$element.on("click." + this.type, this.options.selector, a.proxy(this.toggle, this));
            else if ("manual" != g) {
                var h = "hover" == g ? "mouseenter": "focusin",
                i = "hover" == g ? "mouseleave": "focusout";
                this.$element.on(h + "." + this.type, this.options.selector, a.proxy(this.enter, this)),
                this.$element.on(i + "." + this.type, this.options.selector, a.proxy(this.leave, this))
            }
        }
        this.options.selector ? this._options = a.extend({},
        this.options, {
            trigger: "manual",
            selector: ""
        }) : this.fixTitle()
    },
    c.prototype.getDefaults = function() {
        return c.DEFAULTS
    },
    c.prototype.getOptions = function(b) {
        return b = a.extend({},
        this.getDefaults(), this.$element.data(), b),
        b.delay && "number" == typeof b.delay && (b.delay = {
            show: b.delay,
            hide: b.delay
        }),
        b
    },
    c.prototype.getDelegateOptions = function() {
        var b = {},
        c = this.getDefaults();
        return this._options && a.each(this._options,
        function(a, d) {
            c[a] != d && (b[a] = d)
        }),
        b
    },
    c.prototype.enter = function(b) {
        var c = b instanceof this.constructor ? b: a(b.currentTarget).data("bs." + this.type);
        return c && c.$tip && c.$tip.is(":visible") ? void(c.hoverState = "in") : (c || (c = new this.constructor(b.currentTarget, this.getDelegateOptions()), a(b.currentTarget).data("bs." + this.type, c)), clearTimeout(c.timeout), c.hoverState = "in", c.options.delay && c.options.delay.show ? void(c.timeout = setTimeout(function() {
            "in" == c.hoverState && c.show()
        },
        c.options.delay.show)) : c.show())
    },
    c.prototype.leave = function(b) {
        var c = b instanceof this.constructor ? b: a(b.currentTarget).data("bs." + this.type);
        return c || (c = new this.constructor(b.currentTarget, this.getDelegateOptions()), a(b.currentTarget).data("bs." + this.type, c)),
        clearTimeout(c.timeout),
        c.hoverState = "out",
        c.options.delay && c.options.delay.hide ? void(c.timeout = setTimeout(function() {
            "out" == c.hoverState && c.hide()
        },
        c.options.delay.hide)) : c.hide()
    },
    c.prototype.show = function() {
        var b = a.Event("show.bs." + this.type);
        if (this.hasContent() && this.enabled) {
            this.$element.trigger(b);
            var d = a.contains(this.$element[0].ownerDocument.documentElement, this.$element[0]);
            if (b.isDefaultPrevented() || !d) return;
            var e = this,
            f = this.tip(),
            g = this.getUID(this.type);
            this.setContent(),
            f.attr("id", g),
            this.$element.attr("aria-describedby", g),
            this.options.animation && f.addClass("fade");
            var h = "function" == typeof this.options.placement ? this.options.placement.call(this, f[0], this.$element[0]) : this.options.placement,
            i = /\s?auto?\s?/i,
            j = i.test(h);
            j && (h = h.replace(i, "") || "top"),
            f.detach().css({
                top: 0,
                left: 0,
                display: "block"
            }).addClass(h).data("bs." + this.type, this),
            this.options.container ? f.appendTo(this.options.container) : f.insertAfter(this.$element);
            var k = this.getPosition(),
            l = f[0].offsetWidth,
            m = f[0].offsetHeight;
            if (j) {
                var n = h,
                o = this.options.container ? a(this.options.container) : this.$element.parent(),
                p = this.getPosition(o);
                h = "bottom" == h && k.bottom + m > p.bottom ? "top": "top" == h && k.top - m < p.top ? "bottom": "right" == h && k.right + l > p.width ? "left": "left" == h && k.left - l < p.left ? "right": h,
                f.removeClass(n).addClass(h)
            }
            var q = this.getCalculatedOffset(h, k, l, m);
            this.applyPlacement(q, h);
            var r = function() {
                var a = e.hoverState;
                e.$element.trigger("shown.bs." + e.type),
                e.hoverState = null,
                "out" == a && e.leave(e)
            };
            a.support.transition && this.$tip.hasClass("fade") ? f.one("bsTransitionEnd", r).emulateTransitionEnd(c.TRANSITION_DURATION) : r()
        }
    },
    c.prototype.applyPlacement = function(b, c) {
        var d = this.tip(),
        e = d[0].offsetWidth,
        f = d[0].offsetHeight,
        g = parseInt(d.css("margin-top"), 10),
        h = parseInt(d.css("margin-left"), 10);
        isNaN(g) && (g = 0),
        isNaN(h) && (h = 0),
        b.top = b.top + g,
        b.left = b.left + h,
        a.offset.setOffset(d[0], a.extend({
            using: function(a) {
                d.css({
                    top: Math.round(a.top),
                    left: Math.round(a.left)
                })
            }
        },
        b), 0),
        d.addClass("in");
        var i = d[0].offsetWidth,
        j = d[0].offsetHeight;
        "top" == c && j != f && (b.top = b.top + f - j);
        var k = this.getViewportAdjustedDelta(c, b, i, j);
        k.left ? b.left += k.left: b.top += k.top;
        var l = /top|bottom/.test(c),
        m = l ? 2 * k.left - e + i: 2 * k.top - f + j,
        n = l ? "offsetWidth": "offsetHeight";
        d.offset(b),
        this.replaceArrow(m, d[0][n], l)
    },
    c.prototype.replaceArrow = function(a, b, c) {
        this.arrow().css(c ? "left": "top", 50 * (1 - a / b) + "%").css(c ? "top": "left", "")
    },
    c.prototype.setContent = function() {
        var a = this.tip(),
        b = this.getTitle();
        a.find(".tooltip-inner")[this.options.html ? "html": "text"](b),
        a.removeClass("fade in top bottom left right")
    },
    c.prototype.hide = function(b) {
        function d() {
            "in" != e.hoverState && f.detach(),
            e.$element.removeAttr("aria-describedby").trigger("hidden.bs." + e.type),
            b && b()
        }
        var e = this,
        f = this.tip(),
        g = a.Event("hide.bs." + this.type);
        return this.$element.trigger(g),
        g.isDefaultPrevented() ? void 0 : (f.removeClass("in"), a.support.transition && this.$tip.hasClass("fade") ? f.one("bsTransitionEnd", d).emulateTransitionEnd(c.TRANSITION_DURATION) : d(), this.hoverState = null, this)
    },
    c.prototype.fixTitle = function() {
        var a = this.$element; (a.attr("title") || "string" != typeof a.attr("data-original-title")) && a.attr("data-original-title", a.attr("title") || "").attr("title", "")
    },
    c.prototype.hasContent = function() {
        return this.getTitle()
    },
    c.prototype.getPosition = function(b) {
        b = b || this.$element;
        var c = b[0],
        d = "BODY" == c.tagName,
        e = c.getBoundingClientRect();
        null == e.width && (e = a.extend({},
        e, {
            width: e.right - e.left,
            height: e.bottom - e.top
        }));
        var f = d ? {
            top: 0,
            left: 0
        }: b.offset(),
        g = {
            scroll: d ? document.documentElement.scrollTop || document.body.scrollTop: b.scrollTop()
        },
        h = d ? {
            width: a(window).width(),
            height: a(window).height()
        }: null;
        return a.extend({},
        e, g, h, f)
    },
    c.prototype.getCalculatedOffset = function(a, b, c, d) {
        return "bottom" == a ? {
            top: b.top + b.height,
            left: b.left + b.width / 2 - c / 2
        }: "top" == a ? {
            top: b.top - d,
            left: b.left + b.width / 2 - c / 2
        }: "left" == a ? {
            top: b.top + b.height / 2 - d / 2,
            left: b.left - c
        }: {
            top: b.top + b.height / 2 - d / 2,
            left: b.left + b.width
        }
    },
    c.prototype.getViewportAdjustedDelta = function(a, b, c, d) {
        var e = {
            top: 0,
            left: 0
        };
        if (!this.$viewport) return e;
        var f = this.options.viewport && this.options.viewport.padding || 0,
        g = this.getPosition(this.$viewport);
        if (/right|left/.test(a)) {
            var h = b.top - f - g.scroll,
            i = b.top + f - g.scroll + d;
            h < g.top ? e.top = g.top - h: i > g.top + g.height && (e.top = g.top + g.height - i)
        } else {
            var j = b.left - f,
            k = b.left + f + c;
            j < g.left ? e.left = g.left - j: k > g.width && (e.left = g.left + g.width - k)
        }
        return e
    },
    c.prototype.getTitle = function() {
        var a, b = this.$element,
        c = this.options;
        return a = b.attr("data-original-title") || ("function" == typeof c.title ? c.title.call(b[0]) : c.title)
    },
    c.prototype.getUID = function(a) {
        do a += ~~ (1e6 * Math.random());
        while (document.getElementById(a));
        return a
    },
    c.prototype.tip = function() {
        return this.$tip = this.$tip || a(this.options.template)
    },
    c.prototype.arrow = function() {
        return this.$arrow = this.$arrow || this.tip().find(".tooltip-arrow")
    },
    c.prototype.enable = function() {
        this.enabled = !0
    },
    c.prototype.disable = function() {
        this.enabled = !1
    },
    c.prototype.toggleEnabled = function() {
        this.enabled = !this.enabled
    },
    c.prototype.toggle = function(b) {
        var c = this;
        b && (c = a(b.currentTarget).data("bs." + this.type), c || (c = new this.constructor(b.currentTarget, this.getDelegateOptions()), a(b.currentTarget).data("bs." + this.type, c))),
        c.tip().hasClass("in") ? c.leave(c) : c.enter(c)
    },
    c.prototype.destroy = function() {
        var a = this;
        clearTimeout(this.timeout),
        this.hide(function() {
            a.$element.off("." + a.type).removeData("bs." + a.type)
        })
    };
    var d = a.fn.tooltip;
    a.fn.tooltip = b,
    a.fn.tooltip.Constructor = c,
    a.fn.tooltip.noConflict = function() {
        return a.fn.tooltip = d,
        this
    }
} (jQuery), +
function(a) {
    "use strict";
    function b(b) {
        return this.each(function() {
            var d = a(this),
            e = d.data("bs.popover"),
            f = "object" == typeof b && b,
            g = f && f.selector; (e || "destroy" != b) && (g ? (e || d.data("bs.popover", e = {}), e[g] || (e[g] = new c(this, f))) : e || d.data("bs.popover", e = new c(this, f)), "string" == typeof b && e[b]())
        })
    }
    var c = function(a, b) {
        this.init("popover", a, b)
    };
    if (!a.fn.tooltip) throw new Error("Popover requires tooltip.js");
    c.VERSION = "3.3.1",
    c.DEFAULTS = a.extend({},
    a.fn.tooltip.Constructor.DEFAULTS, {
        placement: "right",
        trigger: "click",
        content: "",
        template: '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
    }),
    c.prototype = a.extend({},
    a.fn.tooltip.Constructor.prototype),
    c.prototype.constructor = c,
    c.prototype.getDefaults = function() {
        return c.DEFAULTS
    },
    c.prototype.setContent = function() {
        var a = this.tip(),
        b = this.getTitle(),
        c = this.getContent();
        a.find(".popover-title")[this.options.html ? "html": "text"](b),
        a.find(".popover-content").children().detach().end()[this.options.html ? "string" == typeof c ? "html": "append": "text"](c),
        a.removeClass("fade top bottom left right in"),
        a.find(".popover-title").html() || a.find(".popover-title").hide()
    },
    c.prototype.hasContent = function() {
        return this.getTitle() || this.getContent()
    },
    c.prototype.getContent = function() {
        var a = this.$element,
        b = this.options;
        return a.attr("data-content") || ("function" == typeof b.content ? b.content.call(a[0]) : b.content)
    },
    c.prototype.arrow = function() {
        return this.$arrow = this.$arrow || this.tip().find(".arrow")
    },
    c.prototype.tip = function() {
        return this.$tip || (this.$tip = a(this.options.template)),
        this.$tip
    };
    var d = a.fn.popover;
    a.fn.popover = b,
    a.fn.popover.Constructor = c,
    a.fn.popover.noConflict = function() {
        return a.fn.popover = d,
        this
    }
} (jQuery), +
function(a) {
    "use strict";
    function b(c, d) {
        var e = a.proxy(this.process, this);
        this.$body = a("body"),
        this.$scrollElement = a(a(c).is("body") ? window: c),
        this.options = a.extend({},
        b.DEFAULTS, d),
        this.selector = (this.options.target || "") + " .nav li > a",
        this.offsets = [],
        this.targets = [],
        this.activeTarget = null,
        this.scrollHeight = 0,
        this.$scrollElement.on("scroll.bs.scrollspy", e),
        this.refresh(),
        this.process()
    }
    function c(c) {
        return this.each(function() {
            var d = a(this),
            e = d.data("bs.scrollspy"),
            f = "object" == typeof c && c;
            e || d.data("bs.scrollspy", e = new b(this, f)),
            "string" == typeof c && e[c]()
        })
    }
    b.VERSION = "3.3.1",
    b.DEFAULTS = {
        offset: 10
    },
    b.prototype.getScrollHeight = function() {
        return this.$scrollElement[0].scrollHeight || Math.max(this.$body[0].scrollHeight, document.documentElement.scrollHeight)
    },
    b.prototype.refresh = function() {
        var b = "offset",
        c = 0;
        a.isWindow(this.$scrollElement[0]) || (b = "position", c = this.$scrollElement.scrollTop()),
        this.offsets = [],
        this.targets = [],
        this.scrollHeight = this.getScrollHeight();
        var d = this;
        this.$body.find(this.selector).map(function() {
            var d = a(this),
            e = d.data("target") || d.attr("href"),
            f = /^#./.test(e) && a(e);
            return f && f.length && f.is(":visible") && [[f[b]().top + c, e]] || null
        }).sort(function(a, b) {
            return a[0] - b[0]
        }).each(function() {
            d.offsets.push(this[0]),
            d.targets.push(this[1])
        })
    },
    b.prototype.process = function() {
        var a, b = this.$scrollElement.scrollTop() + this.options.offset,
        c = this.getScrollHeight(),
        d = this.options.offset + c - this.$scrollElement.height(),
        e = this.offsets,
        f = this.targets,
        g = this.activeTarget;
        if (this.scrollHeight != c && this.refresh(), b >= d) return g != (a = f[f.length - 1]) && this.activate(a);
        if (g && b < e[0]) return this.activeTarget = null,
        this.clear();
        for (a = e.length; a--;) g != f[a] && b >= e[a] && (!e[a + 1] || b <= e[a + 1]) && this.activate(f[a])
    },
    b.prototype.activate = function(b) {
        this.activeTarget = b,
        this.clear();
        var c = this.selector + '[data-target="' + b + '"],' + this.selector + '[href="' + b + '"]',
        d = a(c).parents("li").addClass("active");
        d.parent(".dropdown-menu").length && (d = d.closest("li.dropdown").addClass("active")),
        d.trigger("activate.bs.scrollspy")
    },
    b.prototype.clear = function() {
        a(this.selector).parentsUntil(this.options.target, ".active").removeClass("active")
    };
    var d = a.fn.scrollspy;
    a.fn.scrollspy = c,
    a.fn.scrollspy.Constructor = b,
    a.fn.scrollspy.noConflict = function() {
        return a.fn.scrollspy = d,
        this
    },
    a(window).on("load.bs.scrollspy.data-api",
    function() {
        a('[data-spy="scroll"]').each(function() {
            var b = a(this);
            c.call(b, b.data())
        })
    })
} (jQuery), +
function(a) {
    "use strict";
    function b(b) {
        return this.each(function() {
            var d = a(this),
            e = d.data("bs.tab");
            e || d.data("bs.tab", e = new c(this)),
            "string" == typeof b && e[b]()
        })
    }
    var c = function(b) {
        this.element = a(b)
    };
    c.VERSION = "3.3.1",
    c.TRANSITION_DURATION = 150,
    c.prototype.show = function() {
        var b = this.element,
        c = b.closest("ul:not(.dropdown-menu)"),
        d = b.data("target");
        if (d || (d = b.attr("href"), d = d && d.replace(/.*(?=#[^\s]*$)/, "")), !b.parent("li").hasClass("active")) {
            var e = c.find(".active:last a"),
            f = a.Event("hide.bs.tab", {
                relatedTarget: b[0]
            }),
            g = a.Event("show.bs.tab", {
                relatedTarget: e[0]
            });
            if (e.trigger(f), b.trigger(g), !g.isDefaultPrevented() && !f.isDefaultPrevented()) {
                var h = a(d);
                this.activate(b.closest("li"), c),
                this.activate(h, h.parent(),
                function() {
                    e.trigger({
                        type: "hidden.bs.tab",
                        relatedTarget: b[0]
                    }),
                    b.trigger({
                        type: "shown.bs.tab",
                        relatedTarget: e[0]
                    })
                })
            }
        }
    },
    c.prototype.activate = function(b, d, e) {
        function f() {
            g.removeClass("active").find("> .dropdown-menu > .active").removeClass("active").end().find('[data-toggle="tab"]').attr("aria-expanded", !1),
            b.addClass("active").find('[data-toggle="tab"]').attr("aria-expanded", !0),
            h ? (b[0].offsetWidth, b.addClass("in")) : b.removeClass("fade"),
            b.parent(".dropdown-menu") && b.closest("li.dropdown").addClass("active").end().find('[data-toggle="tab"]').attr("aria-expanded", !0),
            e && e()
        }
        var g = d.find("> .active"),
        h = e && a.support.transition && (g.length && g.hasClass("fade") || !!d.find("> .fade").length);
        g.length && h ? g.one("bsTransitionEnd", f).emulateTransitionEnd(c.TRANSITION_DURATION) : f(),
        g.removeClass("in")
    };
    var d = a.fn.tab;
    a.fn.tab = b,
    a.fn.tab.Constructor = c,
    a.fn.tab.noConflict = function() {
        return a.fn.tab = d,
        this
    };
    var e = function(c) {
        c.preventDefault(),
        b.call(a(this), "show")
    };
    a(document).on("click.bs.tab.data-api", '[data-toggle="tab"]', e).on("click.bs.tab.data-api", '[data-toggle="pill"]', e)
} (jQuery), +
function(a) {
    "use strict";
    function b(b) {
        return this.each(function() {
            var d = a(this),
            e = d.data("bs.affix"),
            f = "object" == typeof b && b;
            e || d.data("bs.affix", e = new c(this, f)),
            "string" == typeof b && e[b]()
        })
    }
    var c = function(b, d) {
        this.options = a.extend({},
        c.DEFAULTS, d),
        this.$target = a(this.options.target).on("scroll.bs.affix.data-api", a.proxy(this.checkPosition, this)).on("click.bs.affix.data-api", a.proxy(this.checkPositionWithEventLoop, this)),
        this.$element = a(b),
        this.affixed = this.unpin = this.pinnedOffset = null,
        this.checkPosition()
    };
    c.VERSION = "3.3.1",
    c.RESET = "affix affix-top affix-bottom",
    c.DEFAULTS = {
        offset: 0,
        target: window
    },
    c.prototype.getState = function(a, b, c, d) {
        var e = this.$target.scrollTop(),
        f = this.$element.offset(),
        g = this.$target.height();
        if (null != c && "top" == this.affixed) return c > e ? "top": !1;
        if ("bottom" == this.affixed) return null != c ? e + this.unpin <= f.top ? !1 : "bottom": a - d >= e + g ? !1 : "bottom";
        var h = null == this.affixed,
        i = h ? e: f.top,
        j = h ? g: b;
        return null != c && c >= i ? "top": null != d && i + j >= a - d ? "bottom": !1
    },
    c.prototype.getPinnedOffset = function() {
        if (this.pinnedOffset) return this.pinnedOffset;
        this.$element.removeClass(c.RESET).addClass("affix");
        var a = this.$target.scrollTop(),
        b = this.$element.offset();
        return this.pinnedOffset = b.top - a
    },
    c.prototype.checkPositionWithEventLoop = function() {
        setTimeout(a.proxy(this.checkPosition, this), 1)
    },
    c.prototype.checkPosition = function() {
        if (this.$element.is(":visible")) {
            var b = this.$element.height(),
            d = this.options.offset,
            e = d.top,
            f = d.bottom,
            g = a("body").height();
            "object" != typeof d && (f = e = d),
            "function" == typeof e && (e = d.top(this.$element)),
            "function" == typeof f && (f = d.bottom(this.$element));
            var h = this.getState(g, b, e, f);
            if (this.affixed != h) {
                null != this.unpin && this.$element.css("top", "");
                var i = "affix" + (h ? "-" + h: ""),
                j = a.Event(i + ".bs.affix");
                if (this.$element.trigger(j), j.isDefaultPrevented()) return;
                this.affixed = h,
                this.unpin = "bottom" == h ? this.getPinnedOffset() : null,
                this.$element.removeClass(c.RESET).addClass(i).trigger(i.replace("affix", "affixed") + ".bs.affix")
            }
            "bottom" == h && this.$element.offset({
                top: g - b - f
            })
        }
    };
    var d = a.fn.affix;
    a.fn.affix = b,
    a.fn.affix.Constructor = c,
    a.fn.affix.noConflict = function() {
        return a.fn.affix = d,
        this
    },
    a(window).on("load",
    function() {
        a('[data-spy="affix"]').each(function() {
            var c = a(this),
            d = c.data();
            d.offset = d.offset || {},
            null != d.offsetBottom && (d.offset.bottom = d.offsetBottom),
            null != d.offsetTop && (d.offset.top = d.offsetTop),
            b.call(c, d)
        })
    })
} (jQuery),
function() {
    var a, b, c, d, e, f = {}.hasOwnProperty,
    g = function(a, b) {
        function c() {
            this.constructor = a
        }
        for (var d in b) f.call(b, d) && (a[d] = b[d]);
        return c.prototype = b.prototype,
        a.prototype = new c,
        a.__super__ = b.prototype,
        a
    };
    d = function() {
        function a() {
            this.options_index = 0,
            this.parsed = []
        }
        return a.prototype.add_node = function(a) {
            return "OPTGROUP" === a.nodeName.toUpperCase() ? this.add_group(a) : this.add_option(a)
        },
        a.prototype.add_group = function(a) {
            var b, c, d, e, f, g;
            for (b = this.parsed.length, this.parsed.push({
                array_index: b,
                group: !0,
                label: this.escapeExpression(a.label),
                children: 0,
                disabled: a.disabled
            }), f = a.childNodes, g = [], d = 0, e = f.length; e > d; d++) c = f[d],
            g.push(this.add_option(c, b, a.disabled));
            return g
        },
        a.prototype.add_option = function(a, b, c) {
            return "OPTION" === a.nodeName.toUpperCase() ? ("" !== a.text ? (null != b && (this.parsed[b].children += 1), this.parsed.push({
                array_index: this.parsed.length,
                options_index: this.options_index,
                value: a.value,
                text: a.text,
                html: a.innerHTML,
                selected: a.selected,
                disabled: c === !0 ? c: a.disabled,
                group_array_index: b,
                classes: a.className,
                style: a.style.cssText
            })) : this.parsed.push({
                array_index: this.parsed.length,
                options_index: this.options_index,
                empty: !0
            }), this.options_index += 1) : void 0
        },
        a.prototype.escapeExpression = function(a) {
            var b, c;
            return null == a || a === !1 ? "": /[\&\<\>\"\'\`]/.test(a) ? (b = {
                "<": "&lt;",
                ">": "&gt;",
                '"': "&quot;",
                "'": "&#x27;",
                "`": "&#x60;"
            },
            c = /&(?!\w+;)|[\<\>\"\'\`]/g, a.replace(c,
            function(a) {
                return b[a] || "&amp;"
            })) : a
        },
        a
    } (),
    d.select_to_array = function(a) {
        var b, c, e, f, g;
        for (c = new d, g = a.childNodes, e = 0, f = g.length; f > e; e++) b = g[e],
        c.add_node(b);
        return c.parsed
    },
    b = function() {
        function a(b, c) {
            this.form_field = b,
            this.options = null != c ? c: {},
            a.browser_is_supported() && (this.is_multiple = this.form_field.multiple, this.set_default_text(), this.set_default_values(), this.setup(), this.set_up_html(), this.register_observers())
        }
        return a.prototype.set_default_values = function() {
            var a = this;
            return this.click_test_action = function(b) {
                return a.test_active_click(b)
            },
            this.activate_action = function(b) {
                return a.activate_field(b)
            },
            this.active_field = !1,
            this.mouse_on_container = !1,
            this.results_showing = !1,
            this.result_highlighted = null,
            this.allow_single_deselect = null != this.options.allow_single_deselect && null != this.form_field.options[0] && "" === this.form_field.options[0].text ? this.options.allow_single_deselect: !1,
            this.disable_search_threshold = this.options.disable_search_threshold || 0,
            this.disable_search = this.options.disable_search || !1,
            this.enable_split_word_search = null != this.options.enable_split_word_search ? this.options.enable_split_word_search: !0,
            this.group_search = null != this.options.group_search ? this.options.group_search: !0,
            this.search_contains = this.options.search_contains || !1,
            this.single_backstroke_delete = null != this.options.single_backstroke_delete ? this.options.single_backstroke_delete: !0,
            this.max_selected_options = this.options.max_selected_options || 1 / 0,
            this.inherit_select_classes = this.options.inherit_select_classes || !1,
            this.display_selected_options = null != this.options.display_selected_options ? this.options.display_selected_options: !0,
            this.display_disabled_options = null != this.options.display_disabled_options ? this.options.display_disabled_options: !0
        },
        a.prototype.set_default_text = function() {
            return this.default_text = this.form_field.getAttribute("data-placeholder") ? this.form_field.getAttribute("data-placeholder") : this.is_multiple ? this.options.placeholder_text_multiple || this.options.placeholder_text || a.default_multiple_text: this.options.placeholder_text_single || this.options.placeholder_text || a.default_single_text,
            this.results_none_found = this.form_field.getAttribute("data-no_results_text") || this.options.no_results_text || a.default_no_result_text
        },
        a.prototype.mouse_enter = function() {
            return this.mouse_on_container = !0
        },
        a.prototype.mouse_leave = function() {
            return this.mouse_on_container = !1
        },
        a.prototype.input_focus = function() {
            var a = this;
            if (this.is_multiple) {
                if (!this.active_field) return setTimeout(function() {
                    return a.container_mousedown()
                },
                50)
            } else if (!this.active_field) return this.activate_field()
        },
        a.prototype.input_blur = function() {
            var a = this;
            return this.mouse_on_container ? void 0 : (this.active_field = !1, setTimeout(function() {
                return a.blur_test()
            },
            100))
        },
        a.prototype.results_option_build = function(a) {
            var b, c, d, e, f;
            for (b = "", f = this.results_data, d = 0, e = f.length; e > d; d++) c = f[d],
            b += c.group ? this.result_add_group(c) : this.result_add_option(c),
            (null != a ? a.first: void 0) && (c.selected && this.is_multiple ? this.choice_build(c) : c.selected && !this.is_multiple && this.single_set_selected_text(c.text));
            return b
        },
        a.prototype.result_add_option = function(a) {
            var b, c;
            return a.search_match && this.include_option_in_results(a) ? (b = [], a.disabled || a.selected && this.is_multiple || b.push("active-result"), !a.disabled || a.selected && this.is_multiple || b.push("disabled-result"), a.selected && b.push("result-selected"), null != a.group_array_index && b.push("group-option"), "" !== a.classes && b.push(a.classes), c = document.createElement("li"), c.className = b.join(" "), c.style.cssText = a.style, c.setAttribute("data-option-array-index", a.array_index), c.innerHTML = a.search_text, this.outerHTML(c)) : ""
        },
        a.prototype.result_add_group = function(a) {
            var b;
            return (a.search_match || a.group_match) && a.active_options > 0 ? (b = document.createElement("li"), b.className = "group-result", b.innerHTML = a.search_text, this.outerHTML(b)) : ""
        },
        a.prototype.results_update_field = function() {
            return this.set_default_text(),
            this.is_multiple || this.results_reset_cleanup(),
            this.result_clear_highlight(),
            this.results_build(),
            this.results_showing ? this.winnow_results() : void 0
        },
        a.prototype.reset_single_select_options = function() {
            var a, b, c, d, e;
            for (d = this.results_data, e = [], b = 0, c = d.length; c > b; b++) a = d[b],
            e.push(a.selected ? a.selected = !1 : void 0);
            return e
        },
        a.prototype.results_toggle = function() {
            return this.results_showing ? this.results_hide() : this.results_show()
        },
        a.prototype.results_search = function() {
            return this.results_showing ? this.winnow_results() : this.results_show()
        },
        a.prototype.winnow_results = function() {
            var a, b, c, d, e, f, g, h, i, j, k, l;
            for (this.no_results_clear(), d = 0, f = this.get_search_text(), a = f.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&"), i = new RegExp(a, "i"), c = this.get_search_regex(a), l = this.results_data, j = 0, k = l.length; k > j; j++) b = l[j],
            b.search_match = !1,
            e = null,
            this.include_option_in_results(b) && (b.group && (b.group_match = !1, b.active_options = 0), null != b.group_array_index && this.results_data[b.group_array_index] && (e = this.results_data[b.group_array_index], 0 === e.active_options && e.search_match && (d += 1), e.active_options += 1), (!b.group || this.group_search) && (b.search_text = b.group ? b.label: b.text, b.search_match = this.search_string_match(b.search_text, c), b.search_match && !b.group && (d += 1), b.search_match ? (f.length && (g = b.search_text.search(i), h = b.search_text.substr(0, g + f.length) + "</em>" + b.search_text.substr(g + f.length), b.search_text = h.substr(0, g) + "<em>" + h.substr(g)), null != e && (e.group_match = !0)) : null != b.group_array_index && this.results_data[b.group_array_index].search_match && (b.search_match = !0)));
            return this.result_clear_highlight(),
            1 > d && f.length ? (this.update_results_content(""), this.no_results(f)) : (this.update_results_content(this.results_option_build()), this.winnow_results_set_highlight())
        },
        a.prototype.get_search_regex = function(a) {
            var b;
            return b = this.search_contains ? "": "^",
            new RegExp(b + a, "i")
        },
        a.prototype.search_string_match = function(a, b) {
            var c, d, e, f;
            if (b.test(a)) return ! 0;
            if (this.enable_split_word_search && (a.indexOf(" ") >= 0 || 0 === a.indexOf("[")) && (d = a.replace(/\[|\]/g, "").split(" "), d.length)) for (e = 0, f = d.length; f > e; e++) if (c = d[e], b.test(c)) return ! 0
        },
        a.prototype.choices_count = function() {
            var a, b, c, d;
            if (null != this.selected_option_count) return this.selected_option_count;
            for (this.selected_option_count = 0, d = this.form_field.options, b = 0, c = d.length; c > b; b++) a = d[b],
            a.selected && (this.selected_option_count += 1);
            return this.selected_option_count
        },
        a.prototype.choices_click = function(a) {
            return a.preventDefault(),
            this.results_showing || this.is_disabled ? void 0 : this.results_show()
        },
        a.prototype.keyup_checker = function(a) {
            var b, c;
            switch (b = null != (c = a.which) ? c: a.keyCode, this.search_field_scale(), b) {
            case 8:
                if (this.is_multiple && this.backstroke_length < 1 && this.choices_count() > 0) return this.keydown_backstroke();
                if (!this.pending_backstroke) return this.result_clear_highlight(),
                this.results_search();
                break;
            case 13:
                if (a.preventDefault(), this.results_showing) return this.result_select(a);
                break;
            case 27:
                return this.results_showing && this.results_hide(),
                !0;
            case 9:
            case 38:
            case 40:
            case 16:
            case 91:
            case 17:
                break;
            default:
                return this.results_search()
            }
        },
        a.prototype.clipboard_event_checker = function() {
            var a = this;
            return setTimeout(function() {
                return a.results_search()
            },
            50)
        },
        a.prototype.container_width = function() {
            return null != this.options.width ? this.options.width: "" + this.form_field.offsetWidth + "px"
        },
        a.prototype.include_option_in_results = function(a) {
            return this.is_multiple && !this.display_selected_options && a.selected ? !1 : !this.display_disabled_options && a.disabled ? !1 : a.empty ? !1 : !0
        },
        a.prototype.search_results_touchstart = function(a) {
            return this.touch_started = !0,
            this.search_results_mouseover(a)
        },
        a.prototype.search_results_touchmove = function(a) {
            return this.touch_started = !1,
            this.search_results_mouseout(a)
        },
        a.prototype.search_results_touchend = function(a) {
            return this.touch_started ? this.search_results_mouseup(a) : void 0
        },
        a.prototype.outerHTML = function(a) {
            var b;
            return a.outerHTML ? a.outerHTML: (b = document.createElement("div"), b.appendChild(a), b.innerHTML)
        },
        a.browser_is_supported = function() {
            return "Microsoft Internet Explorer" === window.navigator.appName ? document.documentMode >= 8 : /iP(od|hone)/i.test(window.navigator.userAgent) ? !1 : /Android/i.test(window.navigator.userAgent) && /Mobile/i.test(window.navigator.userAgent) ? !1 : !0
        },
        a.default_multiple_text = "Select Some Options",
        a.default_single_text = "Select an Option",
        a.default_no_result_text = "No results match",
        a
    } (),
    a = jQuery,
    a.fn.extend({
        chosen: function(d) {
            return b.browser_is_supported() ? this.each(function() {
                var b, e;
                b = a(this),
                e = b.data("chosen"),
                "destroy" === d && e instanceof c ? e.destroy() : e instanceof c || b.data("chosen", new c(this, d))
            }) : this
        }
    }),
    c = function(b) {
        function c() {
            return e = c.__super__.constructor.apply(this, arguments)
        }
        return g(c, b),
        c.prototype.setup = function() {
            return this.form_field_jq = a(this.form_field),
            this.current_selectedIndex = this.form_field.selectedIndex,
            this.is_rtl = this.form_field_jq.hasClass("chosen-rtl")
        },
        c.prototype.set_up_html = function() {
            var b, c;
            return b = ["chosen-container"],
            b.push("chosen-container-" + (this.is_multiple ? "multi": "single")),
            this.inherit_select_classes && this.form_field.className && b.push(this.form_field.className),
            this.is_rtl && b.push("chosen-rtl"),
            c = {
                "class": b.join(" "),
                style: "width: " + this.container_width() + ";",
                title: this.form_field.title
            },
            this.form_field.id.length && (c.id = this.form_field.id.replace(/[^\w]/g, "_") + "_chosen"),
            this.container = a("<div />", c),
            this.container.html(this.is_multiple ? '<ul class="chosen-choices"><li class="search-field"><input type="text" value="' + this.default_text + '" class="default" autocomplete="off" style="width:25px;" /></li></ul><div class="chosen-drop"><ul class="chosen-results"></ul></div>': '<a class="chosen-single chosen-default" tabindex="-1"><span>' + this.default_text + '</span><div><b></b></div></a><div class="chosen-drop"><div class="chosen-search"><input type="text" autocomplete="off" /></div><ul class="chosen-results"></ul></div>'),
            this.form_field_jq.hide().after(this.container),
            this.dropdown = this.container.find("div.chosen-drop").first(),
            this.search_field = this.container.find("input").first(),
            this.search_results = this.container.find("ul.chosen-results").first(),
            this.search_field_scale(),
            this.search_no_results = this.container.find("li.no-results").first(),
            this.is_multiple ? (this.search_choices = this.container.find("ul.chosen-choices").first(), this.search_container = this.container.find("li.search-field").first()) : (this.search_container = this.container.find("div.chosen-search").first(), this.selected_item = this.container.find(".chosen-single").first()),
            this.results_build(),
            this.set_tab_index(),
            this.set_label_behavior(),
            this.form_field_jq.trigger("chosen:ready", {
                chosen: this
            })
        },
        c.prototype.register_observers = function() {
            var a = this;
            return this.container.bind("touchstart.chosen",
            function(b) {
                a.container_mousedown(b)
            }),
            this.container.bind("touchend.chosen",
            function(b) {
                a.container_mouseup(b)
            }),
            this.container.bind("mousedown.chosen",
            function(b) {
                a.container_mousedown(b)
            }),
            this.container.bind("mouseup.chosen",
            function(b) {
                a.container_mouseup(b)
            }),
            this.container.bind("mouseenter.chosen",
            function(b) {
                a.mouse_enter(b)
            }),
            this.container.bind("mouseleave.chosen",
            function(b) {
                a.mouse_leave(b)
            }),
            this.search_results.bind("mouseup.chosen",
            function(b) {
                a.search_results_mouseup(b)
            }),
            this.search_results.bind("mouseover.chosen",
            function(b) {
                a.search_results_mouseover(b)
            }),
            this.search_results.bind("mouseout.chosen",
            function(b) {
                a.search_results_mouseout(b)
            }),
            this.search_results.bind("mousewheel.chosen DOMMouseScroll.chosen",
            function(b) {
                a.search_results_mousewheel(b)
            }),
            this.search_results.bind("touchstart.chosen",
            function(b) {
                a.search_results_touchstart(b)
            }),
            this.search_results.bind("touchmove.chosen",
            function(b) {
                a.search_results_touchmove(b)
            }),
            this.search_results.bind("touchend.chosen",
            function(b) {
                a.search_results_touchend(b)
            }),
            this.form_field_jq.bind("chosen:updated.chosen",
            function(b) {
                a.results_update_field(b)
            }),
            this.form_field_jq.bind("chosen:activate.chosen",
            function(b) {
                a.activate_field(b)
            }),
            this.form_field_jq.bind("chosen:open.chosen",
            function(b) {
                a.container_mousedown(b)
            }),
            this.form_field_jq.bind("chosen:close.chosen",
            function(b) {
                a.input_blur(b)
            }),
            this.search_field.bind("blur.chosen",
            function(b) {
                a.input_blur(b)
            }),
            this.search_field.bind("keyup.chosen",
            function(b) {
                a.keyup_checker(b)
            }),
            this.search_field.bind("keydown.chosen",
            function(b) {
                a.keydown_checker(b)
            }),
            this.search_field.bind("focus.chosen",
            function(b) {
                a.input_focus(b)
            }),
            this.search_field.bind("cut.chosen",
            function(b) {
                a.clipboard_event_checker(b)
            }),
            this.search_field.bind("paste.chosen",
            function(b) {
                a.clipboard_event_checker(b)
            }),
            this.is_multiple ? this.search_choices.bind("click.chosen",
            function(b) {
                a.choices_click(b)
            }) : this.container.bind("click.chosen",
            function(a) {
                a.preventDefault()
            })
        },
        c.prototype.destroy = function() {
            return a(this.container[0].ownerDocument).unbind("click.chosen", this.click_test_action),
            this.search_field[0].tabIndex && (this.form_field_jq[0].tabIndex = this.search_field[0].tabIndex),
            this.container.remove(),
            this.form_field_jq.removeData("chosen"),
            this.form_field_jq.show()
        },
        c.prototype.search_field_disabled = function() {
            return this.is_disabled = this.form_field_jq[0].disabled,
            this.is_disabled ? (this.container.addClass("chosen-disabled"), this.search_field[0].disabled = !0, this.is_multiple || this.selected_item.unbind("focus.chosen", this.activate_action), this.close_field()) : (this.container.removeClass("chosen-disabled"), this.search_field[0].disabled = !1, this.is_multiple ? void 0 : this.selected_item.bind("focus.chosen", this.activate_action))
        },
        c.prototype.container_mousedown = function(b) {
            return this.is_disabled || (b && "mousedown" === b.type && !this.results_showing && b.preventDefault(), null != b && a(b.target).hasClass("search-choice-close")) ? void 0 : (this.active_field ? this.is_multiple || !b || a(b.target)[0] !== this.selected_item[0] && !a(b.target).parents("a.chosen-single").length || (b.preventDefault(), this.results_toggle()) : (this.is_multiple && this.search_field.val(""), a(this.container[0].ownerDocument).bind("click.chosen", this.click_test_action), this.results_show()), this.activate_field())
        },
        c.prototype.container_mouseup = function(a) {
            return "ABBR" !== a.target.nodeName || this.is_disabled ? void 0 : this.results_reset(a)
        },
        c.prototype.search_results_mousewheel = function(a) {
            var b;
            return a.originalEvent && (b = a.originalEvent.deltaY || -a.originalEvent.wheelDelta || a.originalEvent.detail),
            null != b ? (a.preventDefault(), "DOMMouseScroll" === a.type && (b = 40 * b), this.search_results.scrollTop(b + this.search_results.scrollTop())) : void 0
        },
        c.prototype.blur_test = function() {
            return ! this.active_field && this.container.hasClass("chosen-container-active") ? this.close_field() : void 0
        },
        c.prototype.close_field = function() {
            return a(this.container[0].ownerDocument).unbind("click.chosen", this.click_test_action),
            this.active_field = !1,
            this.results_hide(),
            this.container.removeClass("chosen-container-active"),
            this.clear_backstroke(),
            this.show_search_field_default(),
            this.search_field_scale()
        },
        c.prototype.activate_field = function() {
            return this.container.addClass("chosen-container-active"),
            this.active_field = !0,
            this.search_field.val(this.search_field.val()),
            this.search_field.focus()
        },
        c.prototype.test_active_click = function(b) {
            var c;
            return c = a(b.target).closest(".chosen-container"),
            c.length && this.container[0] === c[0] ? this.active_field = !0 : this.close_field()
        },
        c.prototype.results_build = function() {
            return this.parsing = !0,
            this.selected_option_count = null,
            this.results_data = d.select_to_array(this.form_field),
            this.is_multiple ? this.search_choices.find("li.search-choice").remove() : this.is_multiple || (this.single_set_selected_text(), this.disable_search || this.form_field.options.length <= this.disable_search_threshold ? (this.search_field[0].readOnly = !0, this.container.addClass("chosen-container-single-nosearch")) : (this.search_field[0].readOnly = !1, this.container.removeClass("chosen-container-single-nosearch"))),
            this.update_results_content(this.results_option_build({
                first: !0
            })),
            this.search_field_disabled(),
            this.show_search_field_default(),
            this.search_field_scale(),
            this.parsing = !1
        },
        c.prototype.result_do_highlight = function(a) {
            var b, c, d, e, f;
            if (a.length) {
                if (this.result_clear_highlight(), this.result_highlight = a, this.result_highlight.addClass("highlighted"), d = parseInt(this.search_results.css("maxHeight"), 10), f = this.search_results.scrollTop(), e = d + f, c = this.result_highlight.position().top + this.search_results.scrollTop(), b = c + this.result_highlight.outerHeight(), b >= e) return this.search_results.scrollTop(b - d > 0 ? b - d: 0);
                if (f > c) return this.search_results.scrollTop(c)
            }
        },
        c.prototype.result_clear_highlight = function() {
            return this.result_highlight && this.result_highlight.removeClass("highlighted"),
            this.result_highlight = null
        },
        c.prototype.results_show = function() {
            return this.is_multiple && this.max_selected_options <= this.choices_count() ? (this.form_field_jq.trigger("chosen:maxselected", {
                chosen: this
            }), !1) : (this.container.addClass("chosen-with-drop"), this.results_showing = !0, this.search_field.focus(), this.search_field.val(this.search_field.val()), this.winnow_results(), this.form_field_jq.trigger("chosen:showing_dropdown", {
                chosen: this
            }))
        },
        c.prototype.update_results_content = function(a) {
            return this.search_results.html(a)
        },
        c.prototype.results_hide = function() {
            return this.results_showing && (this.result_clear_highlight(), this.container.removeClass("chosen-with-drop"), this.form_field_jq.trigger("chosen:hiding_dropdown", {
                chosen: this
            })),
            this.results_showing = !1
        },
        c.prototype.set_tab_index = function() {
            var a;
            return this.form_field.tabIndex ? (a = this.form_field.tabIndex, this.form_field.tabIndex = -1, this.search_field[0].tabIndex = a) : void 0
        },
        c.prototype.set_label_behavior = function() {
            var b = this;
            return this.form_field_label = this.form_field_jq.parents("label"),
            !this.form_field_label.length && this.form_field.id.length && (this.form_field_label = a("label[for='" + this.form_field.id + "']")),
            this.form_field_label.length > 0 ? this.form_field_label.bind("click.chosen",
            function(a) {
                return b.is_multiple ? b.container_mousedown(a) : b.activate_field()
            }) : void 0
        },
        c.prototype.show_search_field_default = function() {
            return this.is_multiple && this.choices_count() < 1 && !this.active_field ? (this.search_field.val(this.default_text), this.search_field.addClass("default")) : (this.search_field.val(""), this.search_field.removeClass("default"))
        },
        c.prototype.search_results_mouseup = function(b) {
            var c;
            return c = a(b.target).hasClass("active-result") ? a(b.target) : a(b.target).parents(".active-result").first(),
            c.length ? (this.result_highlight = c, this.result_select(b), this.search_field.focus()) : void 0
        },
        c.prototype.search_results_mouseover = function(b) {
            var c;
            return c = a(b.target).hasClass("active-result") ? a(b.target) : a(b.target).parents(".active-result").first(),
            c ? this.result_do_highlight(c) : void 0
        },
        c.prototype.search_results_mouseout = function(b) {
            return a(b.target).hasClass("active-result") ? this.result_clear_highlight() : void 0
        },
        c.prototype.choice_build = function(b) {
            var c, d, e = this;
            return c = a("<li />", {
                "class": "search-choice"
            }).html("<span>" + b.html + "</span>"),
            b.disabled ? c.addClass("search-choice-disabled") : (d = a("<a />", {
                "class": "search-choice-close",
                "data-option-array-index": b.array_index
            }), d.bind("click.chosen",
            function(a) {
                return e.choice_destroy_link_click(a)
            }), c.append(d)),
            this.search_container.before(c)
        },
        c.prototype.choice_destroy_link_click = function(b) {
            return b.preventDefault(),
            b.stopPropagation(),
            this.is_disabled ? void 0 : this.choice_destroy(a(b.target))
        },
        c.prototype.choice_destroy = function(a) {
            return this.result_deselect(a[0].getAttribute("data-option-array-index")) ? (this.show_search_field_default(), this.is_multiple && this.choices_count() > 0 && this.search_field.val().length < 1 && this.results_hide(), a.parents("li").first().remove(), this.search_field_scale()) : void 0
        },
        c.prototype.results_reset = function() {
            return this.reset_single_select_options(),
            this.form_field.options[0].selected = !0,
            this.single_set_selected_text(),
            this.show_search_field_default(),
            this.results_reset_cleanup(),
            this.form_field_jq.trigger("change"),
            this.active_field ? this.results_hide() : void 0
        },
        c.prototype.results_reset_cleanup = function() {
            return this.current_selectedIndex = this.form_field.selectedIndex,
            this.selected_item.find("abbr").remove()
        },
        c.prototype.result_select = function(a) {
            var b, c;
            return this.result_highlight ? (b = this.result_highlight, this.result_clear_highlight(), this.is_multiple && this.max_selected_options <= this.choices_count() ? (this.form_field_jq.trigger("chosen:maxselected", {
                chosen: this
            }), !1) : (this.is_multiple ? b.removeClass("active-result") : this.reset_single_select_options(), c = this.results_data[b[0].getAttribute("data-option-array-index")], c.selected = !0, this.form_field.options[c.options_index].selected = !0, this.selected_option_count = null, this.is_multiple ? this.choice_build(c) : this.single_set_selected_text(c.text), (a.metaKey || a.ctrlKey) && this.is_multiple || this.results_hide(), this.search_field.val(""), (this.is_multiple || this.form_field.selectedIndex !== this.current_selectedIndex) && this.form_field_jq.trigger("change", {
                selected: this.form_field.options[c.options_index].value
            }), this.current_selectedIndex = this.form_field.selectedIndex, this.search_field_scale())) : void 0
        },
        c.prototype.single_set_selected_text = function(a) {
            return null == a && (a = this.default_text),
            a === this.default_text ? this.selected_item.addClass("chosen-default") : (this.single_deselect_control_build(), this.selected_item.removeClass("chosen-default")),
            this.selected_item.find("span").text(a)
        },
        c.prototype.result_deselect = function(a) {
            var b;
            return b = this.results_data[a],
            this.form_field.options[b.options_index].disabled ? !1 : (b.selected = !1, this.form_field.options[b.options_index].selected = !1, this.selected_option_count = null, this.result_clear_highlight(), this.results_showing && this.winnow_results(), this.form_field_jq.trigger("change", {
                deselected: this.form_field.options[b.options_index].value
            }), this.search_field_scale(), !0)
        },
        c.prototype.single_deselect_control_build = function() {
            return this.allow_single_deselect ? (this.selected_item.find("abbr").length || this.selected_item.find("span").first().after('<abbr class="search-choice-close"></abbr>'), this.selected_item.addClass("chosen-single-with-deselect")) : void 0
        },
        c.prototype.get_search_text = function() {
            return this.search_field.val() === this.default_text ? "": a("<div/>").text(a.trim(this.search_field.val())).html()
        },
        c.prototype.winnow_results_set_highlight = function() {
            var a, b;
            return b = this.is_multiple ? [] : this.search_results.find(".result-selected.active-result"),
            a = b.length ? b.first() : this.search_results.find(".active-result").first(),
            null != a ? this.result_do_highlight(a) : void 0
        },
        c.prototype.no_results = function(b) {
            var c;
            return c = a('<li class="no-results">' + this.results_none_found + ' "<span></span>"</li>'),
            c.find("span").first().html(b),
            this.search_results.append(c),
            this.form_field_jq.trigger("chosen:no_results", {
                chosen: this
            })
        },
        c.prototype.no_results_clear = function() {
            return this.search_results.find(".no-results").remove()
        },
        c.prototype.keydown_arrow = function() {
            var a;
            return this.results_showing && this.result_highlight ? (a = this.result_highlight.nextAll("li.active-result").first()) ? this.result_do_highlight(a) : void 0 : this.results_show()
        },
        c.prototype.keyup_arrow = function() {
            var a;
            return this.results_showing || this.is_multiple ? this.result_highlight ? (a = this.result_highlight.prevAll("li.active-result"), a.length ? this.result_do_highlight(a.first()) : (this.choices_count() > 0 && this.results_hide(), this.result_clear_highlight())) : void 0 : this.results_show()
        },
        c.prototype.keydown_backstroke = function() {
            var a;
            return this.pending_backstroke ? (this.choice_destroy(this.pending_backstroke.find("a").first()), this.clear_backstroke()) : (a = this.search_container.siblings("li.search-choice").last(), a.length && !a.hasClass("search-choice-disabled") ? (this.pending_backstroke = a, this.single_backstroke_delete ? this.keydown_backstroke() : this.pending_backstroke.addClass("search-choice-focus")) : void 0)
        },
        c.prototype.clear_backstroke = function() {
            return this.pending_backstroke && this.pending_backstroke.removeClass("search-choice-focus"),
            this.pending_backstroke = null
        },
        c.prototype.keydown_checker = function(a) {
            var b, c;
            switch (b = null != (c = a.which) ? c: a.keyCode, this.search_field_scale(), 8 !== b && this.pending_backstroke && this.clear_backstroke(), b) {
            case 8:
                this.backstroke_length = this.search_field.val().length;
                break;
            case 9:
                this.results_showing && !this.is_multiple && this.result_select(a),
                this.mouse_on_container = !1;
                break;
            case 13:
                this.results_showing && a.preventDefault();
                break;
            case 32:
                this.disable_search && a.preventDefault();
                break;
            case 38:
                a.preventDefault(),
                this.keyup_arrow();
                break;
            case 40:
                a.preventDefault(),
                this.keydown_arrow()
            }
        },
        c.prototype.search_field_scale = function() {
            var b, c, d, e, f, g, h, i, j;
            if (this.is_multiple) {
                for (d = 0, h = 0, f = "position:absolute; left: -1000px; top: -1000px; display:none;", g = ["font-size", "font-style", "font-weight", "font-family", "line-height", "text-transform", "letter-spacing"], i = 0, j = g.length; j > i; i++) e = g[i],
                f += e + ":" + this.search_field.css(e) + ";";
                return b = a("<div />", {
                    style: f
                }),
                b.text(this.search_field.val()),
                a("body").append(b),
                h = b.width() + 25,
                b.remove(),
                c = this.container.outerWidth(),
                h > c - 10 && (h = c - 10),
                this.search_field.css({
                    width: h + "px"
                })
            }
        },
        c
    } (b)
}.call(this), !
function(a) {
    "use strict";
    var b = 37,
    c = null,
    d = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABMAAAATCAYAAAByUDbMAAAAZ0lEQVQ4y2NgGLKgquEuFxBPAGI2ahhWCsS/gDibUoO0gPgxEP8H4ttArEyuQYxAPBdqEAxPBImTY5gjEL9DM+wTENuQahAvEO9DMwiGdwAxOymGJQLxTyD+jgWDxCMZRsEoGAVoAADeemwtPcZI2wAAAABJRU5ErkJggg==",
    e = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABMAAAATCAQAAADYWf5HAAAAkElEQVQoz7XQMQ5AQBCF4dWQSJxC5wwax1Cq1e7BAdxD5SL+Tq/QCM1oNiJidwox0355mXnG/DrEtIQ6azioNZQxI0ykPhTQIwhCR+BmBYtlK7kLJYwWCcJA9M4qdrZrd8pPjZWPtOqdRQy320YSV17OatFC4euts6z39GYMKRPCTKY9UnPQ6P+GtMRfGtPnBCiqhAeJPmkqAAAAAElFTkSuQmCC",
    f = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABMAAAATCAYAAAByUDbMAAAAZUlEQVQ4y2NgGAWjYBSggaqGu5FA/BOIv2PBIPFEUgxjB+IdQPwfC94HxLykus4GiD+hGfQOiB3J8SojEE9EM2wuSJzcsFMG4ttQgx4DsRalkZENxL+AuJQaMcsGxBOAmGvopk8AVz1sLZgg0bsAAAAASUVORK5CYII= ",
    g = function(a) {
        var b = arguments,
        c = !0,
        d = 1;
        return a = a.replace(/%s/g,
        function() {
            var a = b[d++];
            return "undefined" == typeof a ? (c = !1, "") : a
        }),
        c ? a: ""
    },
    h = function(b, c, d, e) {
        var f = "";
        return a.each(b,
        function(a, b) {
            return b[c] === e ? (f = b[d], !1) : !0
        }),
        f
    },
    i = function(b, c) {
        var d = -1;
        return a.each(b,
        function(a, b) {
            return b.field === c ? (d = a, !1) : !0
        }),
        d
    },
    j = function() {
        if (null === c) {
            var b, d, e = a("<p/>").addClass("fixed-table-scroll-inner"),
            f = a("<div/>").addClass("fixed-table-scroll-outer");
            f.append(e),
            a("body").append(f),
            b = e[0].offsetWidth,
            f.css("overflow", "scroll"),
            d = e[0].offsetWidth,
            b === d && (d = f[0].clientWidth),
            f.remove(),
            c = b - d
        }
        return c
    },
    k = function(b, c, d, e) {
        if ("string" == typeof c) {
            var f = c.split(".");
            f.length > 1 ? (c = window, a.each(f,
            function(a, b) {
                c = c[b]
            })) : c = window[c]
        }
        return "object" == typeof c ? c: "function" == typeof c ? c.apply(b, d) : e
    },
    l = function(a) {
        return "string" == typeof a ? a.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#039;") : a
    },
    m = function(b) {
        var c = 0;
        return b.children().each(function() {
            c < a(this).outerHeight(!0) && (c = a(this).outerHeight(!0))
        }),
        c
    },
    n = function(b, c) {
        this.options = c,
        this.$el = a(b),
        this.$el_ = this.$el.clone(),
        this.timeoutId_ = 0,
        this.timeoutFooter_ = 0,
        this.init()
    };
    n.DEFAULTS = {
        classes: "table table-hover",
        height: void 0,
        undefinedText: "-",
        sortName: void 0,
        sortOrder: "asc",
        striped: !1,
        columns: [],
        data: [],
        method: "get",
        url: void 0,
        ajax: void 0,
        cache: !0,
        contentType: "application/json",
        dataType: "json",
        ajaxOptions: {},
        queryParams: function(a) {
            return a
        },
        queryParamsType: "limit",
        responseHandler: function(a) {
            return a
        },
        pagination: !1,
        sidePagination: "client",
        totalRows: 0,
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 25, 50, 100],
        paginationHAlign: "right",
        paginationVAlign: "bottom",
        paginationDetailHAlign: "left",
        paginationFirstText: "&laquo;",
        paginationPreText: "&lsaquo;",
        paginationNextText: "&rsaquo;",
        paginationLastText: "&raquo;",
        search: !1,
        searchAlign: "right",
        selectItemName: "btSelectItem",
        showHeader: !0,
        showFooter: !1,
        showColumns: !1,
        showPaginationSwitch: !1,
        showRefresh: !1,
        showToggle: !1,
        buttonsAlign: "right",
        smartDisplay: !0,
        minimumCountColumns: 1,
        idField: void 0,
        uniqueId: void 0,
        cardView: !1,
        trimOnSearch: !0,
        clickToSelect: !1,
        singleSelect: !1,
        toolbar: void 0,
        toolbarAlign: "left",
        checkboxHeader: !0,
        sortable: !0,
        maintainSelected: !1,
        searchTimeOut: 500,
        searchText: "",
        iconSize: void 0,
        iconsPrefix: "glyphicon",
        icons: {
            paginationSwitchDown: "glyphicon-collapse-down icon-chevron-down",
            paginationSwitchUp: "glyphicon-collapse-up icon-chevron-up",
            refresh: "glyphicon-refresh icon-refresh",
            toggle: "glyphicon-list-alt icon-list-alt",
            columns: "glyphicon-th icon-th"
        },
        rowStyle: function() {
            return {}
        },
        rowAttributes: function() {
            return {}
        },
        onAll: function() {
            return ! 1
        },
        onClickRow: function() {
            return ! 1
        },
        onDblClickRow: function() {
            return ! 1
        },
        onSort: function() {
            return ! 1
        },
        onCheck: function() {
            return ! 1
        },
        onUncheck: function() {
            return ! 1
        },
        onCheckAll: function() {
            return ! 1
        },
        onUncheckAll: function() {
            return ! 1
        },
        onLoadSuccess: function() {
            return ! 1
        },
        onLoadError: function() {
            return ! 1
        },
        onColumnSwitch: function() {
            return ! 1
        },
        onPageChange: function() {
            return ! 1
        },
        onSearch: function() {
            return ! 1
        },
        onToggle: function() {
            return ! 1
        },
        onPreBody: function() {
            return ! 1
        },
        onPostBody: function() {
            return ! 1
        },
        onPostHeader: function() {
            return ! 1
        }
    },
    n.LOCALES = [],
    n.LOCALES["zh-CN"] = {
        formatLoadingMessage: function() {
            return "正在努力地加载数据中，请稍候……"
        },
        formatRecordsPerPage: function(a) {
            return "每页显示 " + a + " 条记录"
        },
        formatShowingRows: function(a, b, c) {
            return "显示第 " + a + " 到第 " + b + " 条记录，总共 " + c + " 条记录"
        },
        formatSearch: function() {
            return "搜索"
        },
        formatNoMatches: function() {
            return "没有找到匹配的记录"
        },
        formatPaginationSwitch: function() {
            return "隐藏/显示分页"
        },
        formatRefresh: function() {
            return "刷新"
        },
        formatToggle: function() {
            return "切换"
        },
        formatColumns: function() {
            return "列"
        },
        formatAllRows: function() {
            return "所有"
        }
    },
    a.extend(n.DEFAULTS, n.LOCALES["zh-CN"]),
    n.COLUMN_DEFAULTS = {
        radio: !1,
        checkbox: !1,
        checkboxEnabled: !0,
        field: void 0,
        title: void 0,
        "class": void 0,
        align: void 0,
        halign: void 0,
        falign: void 0,
        valign: void 0,
        width: void 0,
        sortable: !1,
        order: "asc",
        visible: !0,
        switchable: !0,
        clickToSelect: !0,
        formatter: void 0,
        footerFormatter: void 0,
        events: void 0,
        sorter: void 0,
        sortName: void 0,
        cellStyle: void 0,
        searchable: !0,
        cardVisible: !0
    },
    n.EVENTS = {
        "all.bs.table": "onAll",
        "click-row.bs.table": "onClickRow",
        "dbl-click-row.bs.table": "onDblClickRow",
        "sort.bs.table": "onSort",
        "check.bs.table": "onCheck",
        "uncheck.bs.table": "onUncheck",
        "check-all.bs.table": "onCheckAll",
        "uncheck-all.bs.table": "onUncheckAll",
        "load-success.bs.table": "onLoadSuccess",
        "load-error.bs.table": "onLoadError",
        "column-switch.bs.table": "onColumnSwitch",
        "page-change.bs.table": "onPageChange",
        "search.bs.table": "onSearch",
        "toggle.bs.table": "onToggle",
        "pre-body.bs.table": "onPreBody",
        "post-body.bs.table": "onPostBody",
        "post-header.bs.table": "onPostHeader"
    },
    n.prototype.init = function() {
        this.initContainer(),
        this.initTable(),
        this.initHeader(),
        this.initData(),
        this.initFooter(),
        this.initToolbar(),
        this.initPagination(),
        this.initBody(),
        this.initServer()
    },
    n.prototype.initContainer = function() {
        this.$container = a(['<div class="bootstrap-table">', '<div class="fixed-table-toolbar"></div>', "top" === this.options.paginationVAlign || "both" === this.options.paginationVAlign ? '<div class="fixed-table-pagination" style="clear: both;"></div>': "", '<div class="fixed-table-container">', '<div class="fixed-table-header"><table></table></div>', '<div class="fixed-table-body">', '<div class="fixed-table-loading">', this.options.formatLoadingMessage(), "</div>", "</div>", '<div class="fixed-table-footer"><table><tr></tr></table></div>', "bottom" === this.options.paginationVAlign || "both" === this.options.paginationVAlign ? '<div class="fixed-table-pagination"></div>': "", "</div>", "</div>"].join("")),
        this.$container.insertAfter(this.$el),
        this.$container.find(".fixed-table-body").append(this.$el),
        this.$container.after('<div class="clearfix"></div>'),
        this.$loading = this.$container.find(".fixed-table-loading"),
        this.$el.addClass(this.options.classes),
        this.options.striped && this.$el.addClass("table-striped"),
        -1 !== a.inArray("table-no-bordered", this.options.classes.split(" ")) && this.$container.find(".fixed-table-container").addClass("table-no-bordered")
    },
    n.prototype.initTable = function() {
        var b = this,
        c = [],
        d = [];
        this.$header = this.$el.find("thead"),
        this.$header.length || (this.$header = a("<thead></thead>").appendTo(this.$el)),
        this.$header.find("tr").length || this.$header.append("<tr></tr>"),
        this.$header.find("th").each(function() {
            var b = a.extend({},
            {
                title: a(this).html(),
                "class": a(this).attr("class")
            },
            a(this).data());
            c.push(b)
        }),
        this.options.columns = a.extend(!0, [], c, this.options.columns),
        a.each(this.options.columns,
        function(c, d) {
            b.options.columns[c] = a.extend({},
            n.COLUMN_DEFAULTS, {
                field: c
            },
            d)
        }),
        this.options.data.length || (this.$el.find("tbody tr").each(function() {
            var c = {};
            c._id = a(this).attr("id"),
            c._class = a(this).attr("class"),
            c._data = a(this).data(),
            a(this).find("td").each(function(d) {
                var e = b.options.columns[d].field;
                c[e] = a(this).html(),
                c["_" + e + "_id"] = a(this).attr("id"),
                c["_" + e + "_class"] = a(this).attr("class"),
                c["_" + e + "_data"] = a(this).data()
            }),
            d.push(c)
        }), this.options.data = d)
    },
    n.prototype.initHeader = function() {
        var c = this,
        d = [],
        e = [];
        this.header = {
            fields: [],
            styles: [],
            classes: [],
            formatters: [],
            events: [],
            sorters: [],
            sortNames: [],
            cellStyles: [],
            clickToSelects: [],
            searchables: []
        },
        a.each(this.options.columns,
        function(a, b) {
            var f = "",
            h = "",
            i = "",
            j = "",
            k = g(' class="%s"', b["class"]),
            l = (c.options.sortOrder || b.order, "px"),
            m = b.width;
            return b.visible ? void((!c.options.cardView || b.cardVisible) && (void 0 === b.width || c.options.cardView || "string" == typeof b.width && -1 !== b.width.indexOf("%") && (l = "%"), b.width && "string" == typeof b.width && (m = b.width.replace("%", "").replace("px", "")), h = g("text-align: %s; ", b.halign ? b.halign: b.align), i = g("text-align: %s; ", b.align), j = g("vertical-align: %s; ", b.valign), j += g("width: %s%s; ", b.checkbox || b.radio ? 36 : m, l), d.push(b), c.header.fields.push(b.field), c.header.styles.push(i + j), c.header.classes.push(k), c.header.formatters.push(b.formatter), c.header.events.push(b.events), c.header.sorters.push(b.sorter), c.header.sortNames.push(b.sortName), c.header.cellStyles.push(b.cellStyle), c.header.clickToSelects.push(b.clickToSelect), c.header.searchables.push(b.searchable), e.push("<th", b.checkbox || b.radio ? g(' class="bs-checkbox %s"', b["class"] || "") : k, g(' style="%s"', h + j), ">"), e.push(g('<div class="th-inner %s">', c.options.sortable && b.sortable ? "sortable": "")), f = b.title, b.checkbox && (!c.options.singleSelect && c.options.checkboxHeader && (f = '<input name="btSelectAll" type="checkbox" />'), c.header.stateField = b.field), b.radio && (f = "", c.header.stateField = b.field, c.options.singleSelect = !0), e.push(f), e.push("</div>"), e.push('<div class="fht-cell"></div>'), e.push("</div>"), e.push("</th>"))) : void(b.field === c.options.sortName && c.header.fields.push(b.field))
        }),
        this.$header.find("tr").html(e.join("")),
        this.$header.find("th").each(function(b) {
            a(this).data(d[b])
        }),
        this.$container.off("click", ".th-inner").on("click", ".th-inner",
        function(b) {
            c.options.sortable && a(this).parent().data().sortable && c.onSort(b)
        }),
        !this.options.showHeader || this.options.cardView ? (this.$header.hide(), this.$container.find(".fixed-table-header").hide(), this.$loading.css("top", 0)) : (this.$header.show(), this.$container.find(".fixed-table-header").show(), this.$loading.css("top", b + "px"), this.getCaretHtml()),
        this.$selectAll = this.$header.find('[name="btSelectAll"]'),
        this.$container.off("click", '[name="btSelectAll"]').on("click", '[name="btSelectAll"]',
        function() {
            var b = a(this).prop("checked");
            c[b ? "checkAll": "uncheckAll"]()
        })
    },
    n.prototype.initFooter = function() {
        this.$footer = this.$container.find(".fixed-table-footer"),
        !this.options.showFooter || this.options.cardView ? this.$footer.hide() : this.$footer.show()
    },
    n.prototype.initData = function(a, b) {
        this.data = "append" === b ? this.data.concat(a) : "prepend" === b ? [].concat(a).concat(this.data) : a || this.options.data,
        this.options.data = this.data,
        "server" !== this.options.sidePagination && this.initSort()
    },
    n.prototype.initSort = function() {
        var b = this,
        c = this.options.sortName,
        d = "desc" === this.options.sortOrder ? -1 : 1,
        e = a.inArray(this.options.sortName, this.header.fields); - 1 !== e && this.data.sort(function(f, g) {
            b.header.sortNames[e] && (c = b.header.sortNames[e]);
            var h = f[c],
            i = g[c],
            j = k(b.header, b.header.sorters[e], [h, i]);
            return void 0 !== j ? d * j: ((void 0 === h || null === h) && (h = ""), (void 0 === i || null === i) && (i = ""), a.isNumeric(h) && a.isNumeric(i) ? (h = parseFloat(h), i = parseFloat(i), i > h ? -1 * d: d) : h === i ? 0 : ("string" != typeof h && (h = h.toString()), -1 === h.localeCompare(i) ? -1 * d: d))
        })
    },
    n.prototype.onSort = function(b) {
        var c = a(b.currentTarget).parent(),
        d = this.$header.find("th").eq(c.index());
        return this.$header.add(this.$header_).find("span.order").remove(),
        this.options.sortName === c.data("field") ? this.options.sortOrder = "asc" === this.options.sortOrder ? "desc": "asc": (this.options.sortName = c.data("field"), this.options.sortOrder = "asc" === c.data("order") ? "desc": "asc"),
        this.trigger("sort", this.options.sortName, this.options.sortOrder),
        c.add(d).data("order", this.options.sortOrder),
        this.getCaretHtml(),
        "server" === this.options.sidePagination ? void this.initServer() : (this.initSort(), void this.initBody())
    },
    n.prototype.initToolbar = function() {
        var b, c, d = this,
        e = [],
        f = 0,
        h = 0;
        this.$toolbar = this.$container.find(".fixed-table-toolbar").html(""),
        "string" == typeof this.options.toolbar && a(g('<div class="bars pull-%s"></div>', this.options.toolbarAlign)).appendTo(this.$toolbar).append(a(this.options.toolbar)),
        e = [g('<div class="columns columns-%s btn-group pull-%s">', this.options.buttonsAlign, this.options.buttonsAlign)],
        "string" == typeof this.options.icons && (this.options.icons = k(null, this.options.icons)),
        this.options.showPaginationSwitch && e.push(g('<button class="btn btn-default" type="button" name="paginationSwitch" title="%s">', this.options.formatPaginationSwitch()), g('<i class="%s %s"></i>', this.options.iconsPrefix, this.options.icons.paginationSwitchDown), "</button>"),
        this.options.showRefresh && e.push(g('<button class="btn btn-default' + (void 0 === this.options.iconSize ? "": " btn-" + this.options.iconSize) + '" type="button" name="refresh" title="%s">', this.options.formatRefresh()), g('<i class="%s %s"></i>', this.options.iconsPrefix, this.options.icons.refresh), "</button>"),
        this.options.showToggle && e.push(g('<button class="btn btn-default' + (void 0 === this.options.iconSize ? "": " btn-" + this.options.iconSize) + '" type="button" name="toggle" title="%s">', this.options.formatToggle()), g('<i class="%s %s"></i>', this.options.iconsPrefix, this.options.icons.toggle), "</button>"),
        this.options.showColumns && (e.push(g('<div class="keep-open btn-group" title="%s">', this.options.formatColumns()), '<button type="button" class="btn btn-default' + (void 0 == this.options.iconSize ? "": " btn-" + this.options.iconSize) + ' dropdown-toggle" data-toggle="dropdown">', g('<i class="%s %s"></i>', this.options.iconsPrefix, this.options.icons.columns), ' <span class="caret"></span>', "</button>", '<ul class="dropdown-menu" role="menu">'), a.each(this.options.columns,
        function(a, b) {
            if (! (b.radio || b.checkbox || d.options.cardView && !b.cardVisible)) {
                var c = b.visible ? ' checked="checked"': "";
                b.switchable && (e.push(g('<li><label><input type="checkbox" data-field="%s" value="%s"%s> %s</label></li>', b.field, a, c, b.title)), h++)
            }
        }), e.push("</ul>", "</div>")),
        e.push("</div>"),
        (this.showToolbar || e.length > 2) && this.$toolbar.append(e.join("")),
        this.options.showPaginationSwitch && this.$toolbar.find('button[name="paginationSwitch"]').off("click").on("click", a.proxy(this.togglePagination, this)),
        this.options.showRefresh && this.$toolbar.find('button[name="refresh"]').off("click").on("click", a.proxy(this.refresh, this)),
        this.options.showToggle && this.$toolbar.find('button[name="toggle"]').off("click").on("click",
        function() {
            d.toggleView()
        }),
        this.options.showColumns && (b = this.$toolbar.find(".keep-open"), h <= this.options.minimumCountColumns && b.find("input").prop("disabled", !0), b.find("li").off("click").on("click",
        function(a) {
            a.stopImmediatePropagation()
        }), b.find("input").off("click").on("click",
        function() {
            var b = a(this);
            d.toggleColumn(i(d.options.columns, a(this).data("field")), b.prop("checked"), !1),
            d.trigger("column-switch", a(this).data("field"), b.prop("checked"))
        })),
        this.options.search && (e = [], e.push('<div class="pull-' + this.options.searchAlign + ' search">', g('<input class="form-control' + (void 0 === this.options.iconSize ? "": " input-" + this.options.iconSize) + '" type="text" placeholder="%s">', this.options.formatSearch()), "</div>"), this.$toolbar.append(e.join("")), c = this.$toolbar.find(".search input"), c.off("keyup drop").on("keyup drop",
        function(a) {
            clearTimeout(f),
            f = setTimeout(function() {
                d.onSearch(a)
            },
            d.options.searchTimeOut)
        }), "" !== this.options.searchText && (c.val(this.options.searchText), clearTimeout(f), f = setTimeout(function() {
            c.trigger("keyup")
        },
        d.options.searchTimeOut)))
    },
    n.prototype.onSearch = function(b) {
        var c = a.trim(a(b.currentTarget).val());
        this.options.trimOnSearch && a(b.currentTarget).val() !== c && a(b.currentTarget).val(c),
        c !== this.searchText && (this.searchText = c, this.options.pageNumber = 1, this.initSearch(), this.updatePagination(), this.trigger("search", c))
    },
    n.prototype.initSearch = function() {
        var b = this;
        if ("server" !== this.options.sidePagination) {
            var c = this.searchText && this.searchText.toLowerCase(),
            d = a.isEmptyObject(this.filterColumns) ? null: this.filterColumns;
            this.data = d ? a.grep(this.options.data,
            function(a) {
                for (var b in d) if (a[b] !== d[b]) return ! 1;
                return ! 0
            }) : this.options.data,
            this.data = c ? a.grep(this.data,
            function(d, e) {
                for (var f in d) {
                    f = a.isNumeric(f) ? parseInt(f, 10) : f;
                    var g = d[f];
                    g = k(b.header, b.header.formatters[a.inArray(f, b.header.fields)], [g, d, e], g);
                    var h = a.inArray(f, b.header.fields);
                    if ( - 1 !== h && b.header.searchables[h] && ("string" == typeof g || "number" == typeof g) && -1 !== (g + "").toLowerCase().indexOf(c)) return ! 0
                }
                return ! 1
            }) : this.data
        }
    },
    n.prototype.initPagination = function() {
        if (this.$pagination = this.$container.find(".fixed-table-pagination"), !this.options.pagination) return void this.$pagination.hide();
        this.$pagination.show();
        var b, c, d, e, f, h, i, j, k, l = this,
        m = [],
        n = !1,
        o = this.getData();
        if ("server" !== this.options.sidePagination && (this.options.totalRows = o.length), this.totalPages = 0, this.options.totalRows) {
            if (this.options.pageSize === this.options.formatAllRows()) this.options.pageSize = this.options.totalRows,
            n = !0;
            else if (this.options.pageSize === this.options.totalRows) {
                var p = "string" == typeof this.options.pageList ? this.options.pageList.replace("[", "").replace("]", "").replace(/ /g, "").toLowerCase().split(",") : this.options.pageList;
                p.indexOf(this.options.formatAllRows().toLowerCase()) > -1 && (n = !0)
            }
            this.totalPages = ~~ ((this.options.totalRows - 1) / this.options.pageSize) + 1,
            this.options.totalPages = this.totalPages
        }
        this.totalPages > 0 && this.options.pageNumber > this.totalPages && (this.options.pageNumber = this.totalPages),
        this.pageFrom = (this.options.pageNumber - 1) * this.options.pageSize + 1,
        this.pageTo = this.options.pageNumber * this.options.pageSize,
        this.pageTo > this.options.totalRows && (this.pageTo = this.options.totalRows),
        m.push('<div class="pull-' + this.options.paginationDetailHAlign + ' pagination-detail">', '<span class="pagination-info">', this.options.formatShowingRows(this.pageFrom, this.pageTo, this.options.totalRows), "</span>"),
        m.push('<span class="page-list">');
        var q = [g('<span class="btn-group %s">', "top" === this.options.paginationVAlign || "both" === this.options.paginationVAlign ? "dropdown": "dropup"), '<button type="button" class="btn btn-default ' + (void 0 === this.options.iconSize ? "": " btn-" + this.options.iconSize) + ' dropdown-toggle" data-toggle="dropdown">', '<span class="page-size">', n ? this.options.formatAllRows() : this.options.pageSize, "</span>", ' <span class="caret"></span>', "</button>", '<ul class="dropdown-menu" role="menu">'],
        r = this.options.pageList;
        if ("string" == typeof this.options.pageList) {
            var s = this.options.pageList.replace("[", "").replace("]", "").replace(/ /g, "").split(",");
            r = [],
            a.each(s,
            function(a, b) {
                r.push(b.toUpperCase() === l.options.formatAllRows().toUpperCase() ? l.options.formatAllRows() : +b)
            })
        }
        for (a.each(r,
        function(a, b) {
            if (!l.options.smartDisplay || 0 === a || r[a - 1] <= l.options.totalRows) {
                var c;
                c = n ? b === l.options.formatAllRows() ? ' class="active"': "": b === l.options.pageSize ? ' class="active"': "",
                q.push(g('<li%s><a href="javascript:void(0)">%s</a></li>', c, b))
            }
        }), q.push("</ul></span>"), m.push(this.options.formatRecordsPerPage(q.join(""))), m.push("</span>"), m.push("</div>", '<div class="pull-' + this.options.paginationHAlign + ' pagination">', '<ul class="pagination' + (void 0 === this.options.iconSize ? "": " pagination-" + this.options.iconSize) + '">', '<li class="page-first"><a href="javascript:void(0)">' + this.options.paginationFirstText + "</a></li>", '<li class="page-pre"><a href="javascript:void(0)">' + this.options.paginationPreText + "</a></li>"), this.totalPages < 5 ? (c = 1, d = this.totalPages) : (c = this.options.pageNumber - 2, d = c + 4, 1 > c && (c = 1, d = 5), d > this.totalPages && (d = this.totalPages, c = d - 4)), b = c; d >= b; b++) m.push('<li class="page-number' + (b === this.options.pageNumber ? " active": "") + '">', '<a href="javascript:void(0)">', b, "</a>", "</li>");
        m.push('<li class="page-next"><a href="javascript:void(0)">' + this.options.paginationNextText + "</a></li>", '<li class="page-last"><a href="javascript:void(0)">' + this.options.paginationLastText + "</a></li>", "</ul>", "</div>"),
        this.$pagination.html(m.join("")),
        e = this.$pagination.find(".page-list a"),
        f = this.$pagination.find(".page-first"),
        h = this.$pagination.find(".page-pre"),
        i = this.$pagination.find(".page-next"),
        j = this.$pagination.find(".page-last"),
        k = this.$pagination.find(".page-number"),
        this.options.pageNumber <= 1 && (f.addClass("disabled"), h.addClass("disabled")),
        this.options.pageNumber >= this.totalPages && (i.addClass("disabled"), j.addClass("disabled")),
        this.options.smartDisplay && (this.totalPages <= 1 && this.$pagination.find("div.pagination").hide(), (r.length < 2 || this.options.totalRows <= r[0]) && this.$pagination.find("span.page-list").hide(), this.$pagination[this.getData().length ? "show": "hide"]()),
        n && (this.options.pageSize = this.options.formatAllRows()),
        e.off("click").on("click", a.proxy(this.onPageListChange, this)),
        f.off("click").on("click", a.proxy(this.onPageFirst, this)),
        h.off("click").on("click", a.proxy(this.onPagePre, this)),
        i.off("click").on("click", a.proxy(this.onPageNext, this)),
        j.off("click").on("click", a.proxy(this.onPageLast, this)),
        k.off("click").on("click", a.proxy(this.onPageNumber, this))
    },
    n.prototype.updatePagination = function(b) {
        b && a(b.currentTarget).hasClass("disabled") || (this.options.maintainSelected || this.resetRows(), this.initPagination(), "server" === this.options.sidePagination ? this.initServer() : this.initBody(), this.trigger("page-change", this.options.pageNumber, this.options.pageSize))
    },
    n.prototype.onPageListChange = function(b) {
        var c = a(b.currentTarget);
        c.parent().addClass("active").siblings().removeClass("active"),
        this.options.pageSize = c.text().toUpperCase() === this.options.formatAllRows().toUpperCase() ? this.options.formatAllRows() : +c.text(),
        this.$toolbar.find(".page-size").text(this.options.pageSize),
        this.updatePagination(b)
    },
    n.prototype.onPageFirst = function(a) {
        this.options.pageNumber = 1,
        this.updatePagination(a)
    },
    n.prototype.onPagePre = function(a) {
        this.options.pageNumber--,
        this.updatePagination(a)
    },
    n.prototype.onPageNext = function(a) {
        this.options.pageNumber++,
        this.updatePagination(a)
    },
    n.prototype.onPageLast = function(a) {
        this.options.pageNumber = this.totalPages,
        this.updatePagination(a)
    },
    n.prototype.onPageNumber = function(b) {
        this.options.pageNumber !== +a(b.currentTarget).text() && (this.options.pageNumber = +a(b.currentTarget).text(), this.updatePagination(b))
    },
    n.prototype.initBody = function(b) {
        var c = this,
        d = [],
        e = this.getData();
        this.trigger("pre-body", e),
        this.$body = this.$el.find("tbody"),
        this.$body.length || (this.$body = a("<tbody></tbody>").appendTo(this.$el)),
        this.options.pagination && "server" !== this.options.sidePagination || (this.pageFrom = 1, this.pageTo = e.length);
        for (var f = this.pageFrom - 1; f < this.pageTo; f++) {
            var j, m = e[f],
            n = {},
            o = [],
            p = "",
            q = {},
            r = [];
            if (n = k(this.options, this.options.rowStyle, [m, f], n), n && n.css) for (j in n.css) o.push(j + ": " + n.css[j]);
            if (q = k(this.options, this.options.rowAttributes, [m, f], q)) for (j in q) r.push(g('%s="%s"', j, l(q[j])));
            m._data && !a.isEmptyObject(m._data) && a.each(m._data,
            function(a, b) {
                "index" !== a && (p += g(' data-%s="%s"', a, b))
            }),
            d.push("<tr", g(" %s", r.join(" ")), g(' id="%s"', a.isArray(m) ? void 0 : m._id), g(' class="%s"', n.classes || (a.isArray(m) ? void 0 : m._class)), g(' data-index="%s"', f), g(' data-uniqueid="%s"', m[this.options.uniqueId]), g("%s", p), ">"),
            this.options.cardView && d.push(g('<td colspan="%s">', this.header.fields.length)),
            a.each(this.header.fields,
            function(b, e) {
                var j = "",
                l = m[e],
                p = "",
                q = {},
                r = "",
                s = c.header.classes[b],
                t = "",
                u = c.options.columns[i(c.options.columns, e)];
                if (n = g('style="%s"', o.concat(c.header.styles[b]).join("; ")), l = k(c.header, c.header.formatters[b], [l, m, f], l), m["_" + e + "_id"] && (r = g(' id="%s"', m["_" + e + "_id"])), m["_" + e + "_class"] && (s = g(' class="%s"', m["_" + e + "_class"])), q = k(c.header, c.header.cellStyles[b], [l, m, f], q), q.classes && (s = g(' class="%s"', q.classes)), q.css) {
                    var v = [];
                    for (var w in q.css) v.push(w + ": " + q.css[w]);
                    n = g('style="%s"', v.concat(c.header.styles[b]).join("; "))
                }
                m["_" + e + "_data"] && !a.isEmptyObject(m["_" + e + "_data"]) && a.each(m["_" + e + "_data"],
                function(a, b) {
                    "index" !== a && (t += g(' data-%s="%s"', a, b))
                }),
                u.checkbox || u.radio ? (p = u.checkbox ? "checkbox": p, p = u.radio ? "radio": p, j = [c.options.cardView ? '<div class="card-view">': '<td class="bs-checkbox">', "<input" + g(' data-index="%s"', f) + g(' name="%s"', c.options.selectItemName) + g(' type="%s"', p) + g(' value="%s"', m[c.options.idField]) + g(' checked="%s"', l === !0 || l && l.checked ? "checked": void 0) + g(' disabled="%s"', !u.checkboxEnabled || l && l.disabled ? "disabled": void 0) + " />", c.options.cardView ? "</div>": "</td>"].join(""), m[c.header.stateField] = l === !0 || l && l.checked) : (l = "undefined" == typeof l || null === l ? c.options.undefinedText: l, j = c.options.cardView ? ['<div class="card-view">', c.options.showHeader ? g('<span class="title" %s>%s</span>', n, h(c.options.columns, "field", "title", e)) : "", g('<span class="value">%s</span>', l), "</div>"].join("") : [g("<td%s %s %s %s>", r, s, n, t), l, "</td>"].join(""), c.options.cardView && c.options.smartDisplay && "" === l && (j = "")),
                d.push(j)
            }),
            this.options.cardView && d.push("</td>"),
            d.push("</tr>")
        }
        d.length || d.push('<tr class="no-records-found">', g('<td colspan="%s">%s</td>', this.header.fields.length, this.options.formatNoMatches()), "</tr>"),
        this.$body.html(d.join("")),
        b || this.scrollTo(0),
        this.$body.find("> tr > td").off("click").on("click",
        function() {
            var b = a(this).parent();
            c.trigger("click-row", c.data[b.data("index")], b),
            c.options.clickToSelect && c.header.clickToSelects[b.children().index(a(this))] && b.find(g('[name="%s"]', c.options.selectItemName))[0].click()
        }),
        this.$body.find("tr").off("dblclick").on("dblclick",
        function() {
            c.trigger("dbl-click-row", c.data[a(this).data("index")], a(this))
        }),
        this.$selectItem = this.$body.find(g('[name="%s"]', this.options.selectItemName)),
        this.$selectItem.off("click").on("click",
        function(b) {
            b.stopImmediatePropagation();
            var d = a(this).prop("checked"),
            e = c.data[a(this).data("index")];
            e[c.header.stateField] = d,
            c.trigger(d ? "check": "uncheck", e),
            c.options.singleSelect && (c.$selectItem.not(this).each(function() {
                c.data[a(this).data("index")][c.header.stateField] = !1
            }), c.$selectItem.filter(":checked").not(this).prop("checked", !1)),
            c.updateSelected()
        }),
        a.each(this.header.events,
        function(b, d) {
            if (d) {
                "string" == typeof d && (d = k(null, d));
                for (var e in d) c.$body.find("tr").each(function() {
                    var f = a(this),
                    g = f.find(c.options.cardView ? ".card-view": "td").eq(b),
                    h = e.indexOf(" "),
                    i = e.substring(0, h),
                    j = e.substring(h + 1),
                    k = d[e];
                    g.find(j).off(i).on(i,
                    function(a) {
                        var d = f.data("index"),
                        e = c.data[d],
                        g = e[c.header.fields[b]];
                        k.apply(this, [a, g, e, d])
                    })
                })
            }
        }),
        this.updateSelected(),
        this.resetView(),
        this.trigger("post-body")
    },
    n.prototype.initServer = function(b, c) {
        var d, e = this,
        f = {},
        g = {
            pageSize: this.options.pageSize === this.options.formatAllRows() ? this.options.totalRows: this.options.pageSize,
            pageNumber: this.options.pageNumber,
            searchText: this.searchText,
            sortName: this.options.sortName,
            sortOrder: this.options.sortOrder
        }; (this.options.url || this.options.ajax) && ("limit" === this.options.queryParamsType && (g = {
            search: g.searchText,
            sort: g.sortName,
            order: g.sortOrder
        },
        this.options.pagination && (g.limit = this.options.pageSize === this.options.formatAllRows() ? this.options.totalRows: this.options.pageSize, g.offset = this.options.pageSize === this.options.formatAllRows() ? 0 : this.options.pageSize * (this.options.pageNumber - 1))), a.isEmptyObject(this.filterColumnsPartial) || (g.filter = JSON.stringify(this.filterColumnsPartial, null)), f = k(this.options, this.options.queryParams, [g], f), a.extend(f, c || {}), f !== !1 && (b || this.$loading.show(), d = a.extend({},
        k(null, this.options.ajaxOptions), {
            type: this.options.method,
            url: this.options.url,
            data: "application/json" === this.options.contentType && "post" === this.options.method ? JSON.stringify(f) : f,
            cache: this.options.cache,
            contentType: this.options.contentType,
            dataType: this.options.dataType,
            success: function(a) {
                a = k(e.options, e.options.responseHandler, [a], a),
                e.load(a),
                e.trigger("load-success", a)
            },
            error: function(a) {
                e.trigger("load-error", a.status)
            },
            complete: function() {
                b || e.$loading.hide()
            }
        }), this.options.ajax ? k(this, this.options.ajax, [d], null) : a.ajax(d)))
    },
    n.prototype.getCaretHtml = function() {
        var b = this;
        a.each(this.$header.find("th"),
        function(c, g) {
            a(g).data("field") === b.options.sortName ? a(g).find(".sortable").css("background-image", "url(" + ("desc" === b.options.sortOrder ? f: d) + ")") : a(g).find(".sortable").css("background-image", "url(" + e + ")")
        })
    },
    n.prototype.updateSelected = function() {
        var b = this.$selectItem.filter(":enabled").length === this.$selectItem.filter(":enabled").filter(":checked").length;
        this.$selectAll.add(this.$selectAll_).prop("checked", b),
        this.$selectItem.each(function() {
            a(this).parents("tr")[a(this).prop("checked") ? "addClass": "removeClass"]("selected")
        })
    },
    n.prototype.updateRows = function() {
        var b = this;
        this.$selectItem.each(function() {
            b.data[a(this).data("index")][b.header.stateField] = a(this).prop("checked")
        })
    },
    n.prototype.resetRows = function() {
        var b = this;
        a.each(this.data,
        function(a, c) {
            b.$selectAll.prop("checked", !1),
            b.$selectItem.prop("checked", !1),
            c[b.header.stateField] = !1
        })
    },
    n.prototype.trigger = function(b) {
        var c = Array.prototype.slice.call(arguments, 1);
        b += ".bs.table",
        this.options[n.EVENTS[b]].apply(this.options, c),
        this.$el.trigger(a.Event(b), c),
        this.options.onAll(b, c),
        this.$el.trigger(a.Event("all.bs.table"), [b, c])
    },
    n.prototype.resetHeader = function() {
        clearTimeout(this.timeoutId_),
        this.timeoutId_ = setTimeout(a.proxy(this.fitHeader, this), this.$el.is(":hidden") ? 100 : 0)
    },
    n.prototype.fitHeader = function() {
        var b, c, d, e, f = this;
        return f.$el.is(":hidden") ? void(f.timeoutFooter_ = setTimeout(a.proxy(f.fitHeader, f), 100)) : (b = this.$container.find(".fixed-table-header"), c = this.$container.find(".fixed-table-body"), d = c.get(0), e = d.scrollWidth > d.clientWidth && d.scrollHeight > d.clientHeight + this.$header.height() ? j() : 0, this.$el.css("margin-top", -this.$header.height()), this.$header_ = this.$header.clone(!0, !0), this.$selectAll_ = this.$header_.find('[name="btSelectAll"]'), b.css({
            "margin-right": e
        }).find("table").css("width", this.$el.css("width")).html("").attr("class", this.$el.attr("class")).append(this.$header_), this.$header.find("th").each(function(b) {
            f.$header_.find("th").eq(b).data(a(this).data())
        }), this.$body.find("tr:first-child:not(.no-records-found) > *").each(function(b) {
            f.$header_.find("div.fht-cell").eq(b).width(a(this).innerWidth())
        }), c.off("scroll").on("scroll",
        function() {
            b.scrollLeft(a(this).scrollLeft())
        }), void f.trigger("post-header"))
    },
    n.prototype.resetFooter = function() {
        var b = this,
        c = b.getData(),
        d = [];
        this.options.showFooter && !this.options.cardView && (a.each(this.options.columns,
        function(a, e) {
            var f = "",
            h = "",
            i = g(' class="%s"', e["class"]);
            e.visible && (!b.options.cardView || e.cardVisible) && (f = g("text-align: %s; ", e.falign ? e.falign: e.align), h = g("vertical-align: %s; ", e.valign), d.push("<td", i, g(' style="%s"', f + h), ">"), d.push(k(e, e.footerFormatter, [c], "&nbsp;") || "&nbsp;"), d.push("</td>"))
        }), this.$footer.find("tr").html(d.join("")), clearTimeout(this.timeoutFooter_), this.timeoutFooter_ = setTimeout(a.proxy(this.fitFooter, this), this.$el.is(":hidden") ? 100 : 0))
    },
    n.prototype.fitFooter = function() {
        var b, c, d, e;
        return clearTimeout(this.timeoutFooter_),
        this.$el.is(":hidden") ? void(this.timeoutFooter_ = setTimeout(a.proxy(this.fitFooter, this), 100)) : (b = this.$container.find(".fixed-table-body"), d = this.$el.css("width"), e = d > b.width() ? j() : 0, this.$footer.css({
            "margin-right": e
        }).find("table").css("width", d).attr("class", this.$el.attr("class")), c = this.$footer.find("td"), void b.find("tbody tr:first-child:not(.no-records-found) > td").each(function(b) {
            c.eq(b).outerWidth(a(this).outerWidth())
        }))
    },
    n.prototype.toggleColumn = function(a, b, c) {
        if ( - 1 !== a && (this.options.columns[a].visible = b, this.initHeader(), this.initSearch(), this.initPagination(), this.initBody(), this.options.showColumns)) {
            var d = this.$toolbar.find(".keep-open input").prop("disabled", !1);
            c && d.filter(g('[value="%s"]', a)).prop("checked", b),
            d.filter(":checked").length <= this.options.minimumCountColumns && d.filter(":checked").prop("disabled", !0)
        }
    },
    n.prototype.toggleRow = function(b, c, d) { - 1 !== b && a(this.$body[0]).children().filter(g(c ? '[data-uniqueid="%s"]': '[data-index="%s"]', b))[d ? "show": "hide"]()
    },
    n.prototype.resetView = function(a) {
        var c = this,
        d = 0,
        e = c.$container.find(".fixed-table-container");
        if (a && a.height && (this.options.height = a.height), this.$selectAll.prop("checked", this.$selectItem.length > 0 && this.$selectItem.length === this.$selectItem.filter(":checked").length), this.options.height) {
            var f = m(this.$toolbar),
            g = m(this.$pagination),
            h = this.options.height - f - g;
            e.css("height", h + "px")
        }
        return this.options.cardView ? (c.$el.css("margin-top", "0"), void e.css("padding-bottom", "0")) : (this.options.showHeader && this.options.height ? (this.$container.find(".fixed-table-header").show(), this.resetHeader(), d += b) : (this.$container.find(".fixed-table-header").hide(), this.trigger("post-header")), this.options.showFooter && (this.resetFooter(), this.options.height && (d += b)), this.getCaretHtml(), void e.css("padding-bottom", d + "px"))
    },
    n.prototype.getData = function(b) {
        return ! this.searchText && a.isEmptyObject(this.filterColumns) && a.isEmptyObject(this.filterColumnsPartial) ? b ? this.options.data.slice(this.pageFrom - 1, this.pageTo) : this.options.data: b ? this.data.slice(this.pageFrom - 1, this.pageTo) : this.data
    },
    n.prototype.load = function(b) {
        var c = !1;
        "server" === this.options.sidePagination ? (this.options.totalRows = b.total, c = b.fixedScroll, b = b.rows) : a.isArray(b) || (c = b.fixedScroll, b = b.data),
        this.initData(b),
        this.initSearch(),
        this.initPagination(),
        this.initBody(c)
    },
    n.prototype.append = function(a) {
        this.initData(a, "append"),
        this.initSearch(),
        this.initPagination(),
        this.initBody(!0)
    },
    n.prototype.prepend = function(a) {
        this.initData(a, "prepend"),
        this.initSearch(),
        this.initPagination(),
        this.initBody(!0)
    },
    n.prototype.remove = function(b) {
        var c, d, e = this.options.data.length;
        if (b.hasOwnProperty("field") && b.hasOwnProperty("values")) {
            for (c = e - 1; c >= 0; c--) d = this.options.data[c],
            d.hasOwnProperty(b.field) && -1 !== a.inArray(d[b.field], b.values) && this.options.data.splice(c, 1);
            e !== this.options.data.length && (this.initSearch(), this.initPagination(), this.initBody(!0))
        }
    },
    n.prototype.insertRow = function(a) {
        a.hasOwnProperty("index") && a.hasOwnProperty("row") && (this.data.splice(a.index, 0, a.row), this.initSearch(), this.initPagination(), this.initBody(!0))
    },
    n.prototype.updateRow = function(b) {
        b.hasOwnProperty("index") && b.hasOwnProperty("row") && (a.extend(this.data[b.index], b.row), this.initBody(!0))
    },
    n.prototype.showRow = function(a) {
        a.hasOwnProperty("index") && this.toggleRow(a.index, void 0 === a.isIdField ? !1 : !0, !0)
    },
    n.prototype.hideRow = function(a) {
        a.hasOwnProperty("index") && this.toggleRow(a.index, void 0 === a.isIdField ? !1 : !0, !1)
    },
    n.prototype.getRowsHidden = function(b) {
        var c = a(this.$body[0]).children().filter(":hidden"),
        d = 0;
        if (b) for (; d < c.length; d++) a(c[d]).show();
        return c
    },
    n.prototype.mergeCells = function(b) {
        var c, d, e = b.index,
        f = a.inArray(b.field, this.header.fields),
        g = b.rowspan || 1,
        h = b.colspan || 1,
        i = this.$body.find("tr"),
        j = i.eq(e).find("td").eq(f);
        if (! (0 > e || 0 > f || e >= this.data.length)) {
            for (c = e; e + g > c; c++) for (d = f; f + h > d; d++) i.eq(c).find("td").eq(d).hide();
            j.attr("rowspan", g).attr("colspan", h).show()
        }
    },
    n.prototype.getOptions = function() {
        return this.options
    },
    n.prototype.getSelections = function() {
        var b = this;
        return a.grep(this.data,
        function(a) {
            return a[b.header.stateField]
        })
    },
    n.prototype.getAllSelections = function() {
        var b = this;
        return a.grep(this.options.data,
        function(a) {
            return a[b.header.stateField]
        })
    },
    n.prototype.checkAll = function() {
        this.checkAll_(!0)
    },
    n.prototype.uncheckAll = function() {
        this.checkAll_(!1)
    },
    n.prototype.checkAll_ = function(a) {
        var b;
        a || (b = this.getSelections()),
        this.$selectItem.filter(":enabled").prop("checked", a),
        this.updateRows(),
        this.updateSelected(),
        a && (b = this.getSelections()),
        this.trigger(a ? "check-all": "uncheck-all", b)
    },
    n.prototype.check = function(a) {
        this.check_(!0, a)
    },
    n.prototype.uncheck = function(a) {
        this.check_(!1, a)
    },
    n.prototype.check_ = function(a, b) {
        this.$selectItem.filter(g('[data-index="%s"]', b)).prop("checked", a),
        this.data[b][this.header.stateField] = a,
        this.updateSelected(),
        this.trigger(a ? "check": "uncheck", this.data[b])
    },
    n.prototype.checkBy = function(a) {
        this.checkBy_(!0, a)
    },
    n.prototype.uncheckBy = function(a) {
        this.checkBy_(!1, a)
    },
    n.prototype.checkBy_ = function(b, c) {
        if (c.hasOwnProperty("field") && c.hasOwnProperty("values")) {
            var d = this;
            a.each(this.options.data,
            function(e, f) {
                return f.hasOwnProperty(c.field) ? void( - 1 !== a.inArray(f[c.field], c.values) && (d.$selectItem.filter(g('[data-index="%s"]', e)).prop("checked", b), f[d.header.stateField] = b, d.trigger(b ? "check": "uncheck", f))) : !1
            }),
            this.updateSelected()
        }
    },
    n.prototype.destroy = function() {
        this.$el.insertBefore(this.$container),
        a(this.options.toolbar).insertBefore(this.$el),
        this.$container.next().remove(),
        this.$container.remove(),
        this.$el.html(this.$el_.html()).css("margin-top", "0").attr("class", this.$el_.attr("class") || "")
    },
    n.prototype.showLoading = function() {
        this.$loading.show()
    },
    n.prototype.hideLoading = function() {
        this.$loading.hide()
    },
    n.prototype.togglePagination = function() {
        this.options.pagination = !this.options.pagination;
        var a = this.$toolbar.find('button[name="paginationSwitch"] i');
        this.options.pagination ? a.attr("class", this.options.iconsPrefix + " " + this.options.icons.paginationSwitchDown) : a.attr("class", this.options.iconsPrefix + " " + this.options.icons.paginationSwitchUp),
        this.updatePagination()
    },
    n.prototype.refresh = function(a) {
        a && a.url && (this.options.url = a.url, this.options.pageNumber = 1),
        this.initServer(a && a.silent, a && a.query)
    },
    n.prototype.resetWidth = function() {
        this.options.showHeader && this.options.height && this.fitHeader(),
        this.options.showFooter && this.fitFooter()
    },
    n.prototype.showColumn = function(a) {
        this.toggleColumn(i(this.options.columns, a), !0, !0)
    },
    n.prototype.hideColumn = function(a) {
        this.toggleColumn(i(this.options.columns, a), !1, !0)
    },
    n.prototype.filterBy = function(b) {
        this.filterColumns = a.isEmptyObject(b) ? {}: b,
        this.options.pageNumber = 1,
        this.initSearch(),
        this.updatePagination()
    },
    n.prototype.scrollTo = function(a) {
        var b = this.$container.find(".fixed-table-body");
        return "string" == typeof a && (a = "bottom" === a ? b[0].scrollHeight: 0),
        "number" == typeof a && b.scrollTop(a),
        "undefined" == typeof a ? b.scrollTop() : void 0
    },
    n.prototype.getScrollPosition = function() {
        return this.scrollTo()
    },
    n.prototype.selectPage = function(a) {
        a > 0 && a <= this.options.totalPages && (this.options.pageNumber = a, this.updatePagination())
    },
    n.prototype.prevPage = function() {
        this.options.pageNumber > 1 && (this.options.pageNumber--, this.updatePagination())
    },
    n.prototype.nextPage = function() {
        this.options.pageNumber < this.options.totalPages && (this.options.pageNumber++, this.updatePagination())
    },
    n.prototype.toggleView = function() {
        this.options.cardView = !this.options.cardView,
        this.initHeader(),
        this.initBody(),
        this.trigger("toggle", this.options.cardView)
    };
    var o = ["getOptions", "getSelections", "getAllSelections", "getData", "load", "append", "prepend", "remove", "insertRow", "updateRow", "showRow", "hideRow", "getRowsHidden", "mergeCells", "checkAll", "uncheckAll", "check", "uncheck", "checkBy", "uncheckBy", "refresh", "resetView", "resetWidth", "destroy", "showLoading", "hideLoading", "showColumn", "hideColumn", "filterBy", "scrollTo", "getScrollPosition", "selectPage", "prevPage", "nextPage", "togglePagination", "toggleView"];
    a.fn.bsGrid = function(b, c) {
        var d;
        return this.each(function() {
            var e = a(this),
            f = e.data("bootstrap.table"),
            g = a.extend({},
            n.DEFAULTS, e.data(), "object" == typeof b && b);
            if ("string" == typeof b) {
                if (a.inArray(b, o) < 0) throw new Error("Unknown method: " + b);
                if (!f) return;
                d = f[b](c),
                "destroy" === b && e.removeData("bootstrap.table")
            }
            f || e.data("bootstrap.table", f = new n(this, g))
        }),
        "undefined" == typeof d ? this: d
    },
    a.fn.bsGrid.Constructor = n,
    a.fn.bsGrid.defaults = n.DEFAULTS,
    a.fn.bsGrid.columnDefaults = n.COLUMN_DEFAULTS,
    a.fn.bsGrid.locales = n.LOCALES,
    a.fn.bsGrid.methods = o,
    a(function() {
        a('[data-toggle="table"]').bsGrid()
    })
} (jQuery),
function(a) {
    "use strict";
    function b(b, c) {
        if (b) {
            var d = a(this),
            e = d.data("valueField") || "val",
            f = d.data("textField") || "text",
            g = d.val();
            d.children().remove().end().val([]),
            a.each(b.rows ? b.rows: b,
            function(b, c) {
                var g = d;
                c.optgroup && (g = a("<optgroup></optgroup>").appendTo(d).end(), g.attr("label", void 0 === c.label ? "": "" + c.label).prop("disabled", c.disabled ? !0 : !1)),
                a('<option value=""></option>').appendTo(g).end().val(c[e]).html(c[f]).prop("disabled", c.disabled ? !0 : !1).prop("selected", c.selected ? !0 : !1)
            }),
            c && d.val(g)
        }
    }
    function c() {
        function b() {
            a.each(e,
            function() {
                var b = a(this),
                c = b.data("dict"),
                d = b.data("dictType");
                void 0 !== c && (b.fillSelect("custom" === d ? g[c] : "public" === d ? f[c] : []), b.chosen && b.trigger("chosen:updated"))
            })
        }
        var c = [],
        d = [],
        e = a("select");
        e.each(function() {
            var b = a(this),
            e = b.data("dict"),
            f = b.data("dictType");
            "custom" === f ? -1 === a.inArray(e, d) && d.push(e) : "public" === f && -1 === a.inArray(e, c) && c.push(e)
        });
        var f = {},
        g = {};
        c && c.length && a.ajax({
            url: a.PUBLIC_DICT_SERVICE_URL,
            data: '{"ids": "' + c.join(",") + '","dicts": "' + c.join(",") + '"}',
            type: "POST",
            dataType: "json"
        }).done(function() {
            b()
        }),
        d && d.length && a.ajax({
            url: a.CUSTOM_DICT_SERVICE_URL,
            data: '{"ids": "' + d.join(",") + '","dicts": "' + d.join(",") + '"}',
            type: "POST",
            dataType: "json"
        }).done(function() {
            b()
        })
    }
    a.extend({
        getReq: function(b, c, d, e, f, g) {
            var h = {
                url: b,
                async: !0,
                dataType: "json",
                type: "GET",
                data: {},
                skipNames: [],
                skipHidden: !1,
                skipDisabled: !0
            };
            if (a.extend(h, f), c) {
                var i = a(c).loopBlock(h);
                a.extend(h.data, i || {})
            }
            return a.extend(h.data, g || {}),
            a.ajax(h).done(d).fail(e)
        },
        ajaxReq: function(b, c, d, e, f) {
            var g = {
                url: b,
                async: !0,
                dataType: "json",
                type: "GET",
                data: c
            };
            return a.extend(g, f),
            a.ajax(g).done(d).fail(e)
        }
    }),
    a.extend({
        postReq: function(b, c, d, e, f, g) {
            return a.getReq(b, c, d, e, a.extend(f, {
                type: "POST"
            }), g)
        },
        ajaxLoadContent: function(b, c, d, e, f) {
            return d || (d = "container"),
            a.ajaxReq(b, c,
            function(b) {
                a("#" + d).empty(),
                a("#" + d).html(b)
            },
            function() {
                alert("信息加载失败！")
            },
            {
                dataType: "html",
                type: e ? e: "POST",
                async: f ? !0 : !1
            })
        }
    }),
    a.fn.extend({
        fillSelect: b
    }),
    a.extend({
        PUBLIC_DICT_SERVICE_URL: a.WEBROOT + "/dhccApi/dictService/pubBatchLoad",
        CUSTOM_DICT_SERVICE_URL: a.WEBROOT + "/dhccApi/dictService/batchLoad",
        batCombo: c
    })
} (jQuery), $(function() {
    $.batCombo()
});