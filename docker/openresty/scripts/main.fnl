(local ngx (require :ngx))
(local redis (require :resty.redis))

(fn log-table [tbl]
  (each [k v (pairs tbl)]
    (ngx.log ngx.DEBUG k "=>" v)))

(fn main []
  (local red (redis.new redis))
  (red.set_timeouts red 1000 1000 1000)
  (let [ok (red.connect red "redis" 6379)]
    ;; (local remote-ip ngx.var.remote_addr)
    (tset ngx.header "content-type" "text/html")
    ;; (tset ngx.header "content-length" 5)
    ;; (tset ngx "status" 403)
    (if ok
        (do
          (red.set red "dog" "an animal"))
        (print "Failed to connect"))
    (log-table redis)
    (ngx.say (red.get red "dog"))))

(fn on-error [err]
  (ngx.say err))

(xpcall main on-error)
