FROM openresty/openresty:alpine-fat

RUN apk add --no-cache git
RUN luarocks install fennel 
RUN opm install lua-resty-redis
