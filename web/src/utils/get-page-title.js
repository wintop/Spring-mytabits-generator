import defaultSettings from '@/settings'

const title = defaultSettings.title || 'Springboot 代码生成工具'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
