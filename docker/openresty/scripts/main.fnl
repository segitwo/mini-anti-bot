(require :ngx)

(fn main []
  (local remote-ip ngx.var.remote_addr)
  (tset ngx.header "content-type" "text/html")
  ;; (tset ngx.header "content-length" 5)
  ;; (tset ngx "status" 403)
  (ngx.say"Hello")
  )

(fn on-error [err]
  (ngx.say err))

(xpcall main on-error)
