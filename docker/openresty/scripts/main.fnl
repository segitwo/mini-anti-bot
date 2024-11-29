(fn main []
  (ngx.exit ngx.HTTP_FORBIDDEN)
  )

(fn on-error [err]
  (ngx.say err))

(xpcall main on-error)
